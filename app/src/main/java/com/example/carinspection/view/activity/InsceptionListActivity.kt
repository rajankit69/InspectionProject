package com.example.carinspection.view.activity

import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carinspection.api.ApiClient
import com.example.carinspection.database.InspectionDatabase
import com.example.carinspection.databinding.ActivityInsceptionListBinding
import com.example.carinspection.model.LeadData
import com.example.carinspection.model.LeadResponse
import com.example.carinspection.model.User
import com.example.carinspection.util.AppHelper
import com.example.carinspection.util.SharedPrefrenceHelper
import com.example.carinspection.view.adapter.InspectionListAdapter
import com.example.carinspection.view.dialog.InspectionValidationDialogFragment
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InsceptionListActivity : BaseActivity() ,InspectionListAdapter.ClickListnerOnItem{
    private  var inspectionListAdapter: InspectionListAdapter?=null
    var activityInsceptionListBinding : ActivityInsceptionListBinding?=null
    var leadDataList : List<LeadData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInsceptionListBinding = ActivityInsceptionListBinding.inflate(layoutInflater)
        setContentView(activityInsceptionListBinding?.root)
        setViewData();
    }

    private fun setViewData() {
        fetchData()
    }

    private fun fetchData() {
     try {
         activityInsceptionListBinding?.progerssDialog?.visibility = View.VISIBLE
            val call: Call<LeadResponse> = ApiClient.getClient.getListOfLead(SharedPrefrenceHelper.getUser(this)?.App_Token)

            call.enqueue(object : Callback<LeadResponse> {

                override fun onResponse(
                    call: Call<LeadResponse>,
                    response: Response<LeadResponse>
                ) {
                    activityInsceptionListBinding?.progerssDialog?.visibility = View.GONE
                    response?.body()?.LIST_LEADS?.let {
                        if (!it.isEmpty()) {
                            leadDataList = it
                            launch{
                                InspectionDatabase.getInstance(this@InsceptionListActivity).leadDataDao.insertAll(it)
                            }
                            inspectionListAdapter = InspectionListAdapter(
                                this@InsceptionListActivity,
                                it,
                                this@InsceptionListActivity
                            )
                            // attach adapter to the recycler view
                            activityInsceptionListBinding?.rvList?.layoutManager =
                                LinearLayoutManager(
                                    this@InsceptionListActivity,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                            activityInsceptionListBinding?.rvList?.adapter = inspectionListAdapter
                        }
                    }
                }

                override fun onFailure(call: Call<LeadResponse>, t: Throwable) {
                    activityInsceptionListBinding?.progerssDialog?.visibility = View.GONE
                }

            })
        }catch (e:Exception) {
            e.printStackTrace()
        }

    }

    companion object {
        //constants
        internal const val EXTRAS_POST_TITLE = "post_title"
        internal const val EXTRAS_POST_IMAGE_URL = "post_image_url"

        //starter function
        fun getStartIntent(context: Context): Intent {
            val intent = Intent(context, InsceptionListActivity::class.java)
            return intent
        }
    }

    override fun onClickItem(position: Int) {
        InspectionValidationDialogFragment(leadDataList.get(position).iD,leadDataList.get(position).vALIDATION_CODE).show(supportFragmentManager,"")
    }
}