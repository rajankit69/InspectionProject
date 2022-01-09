package com.example.carinspection.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carinspection.R
import com.example.carinspection.api.ApiClient
import com.example.carinspection.database.InspectionDatabase
import com.example.carinspection.databinding.ActivityInspectionFlowBinding
import com.example.carinspection.model.*
import com.example.carinspection.util.SharedPrefrenceHelper
import com.example.carinspection.view.adapter.InspectionFlowAdapter
import com.example.carinspection.view.adapter.InspectionListAdapter
import com.example.carinspection.view.fragment.FirstFragment
import com.example.carinspection.view.fragment.UploadImageFragment
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InspectionFlowActivity : BaseActivity(), View.OnClickListener,FirstFragment.FirstFragmentInterface,UploadImageFragment.UploadImageFragmentInterface {

    private var step: Int? = 0
    private var leadId : Int?=0
    private var leadData : LeadData?=null
    var listNameDatalist : List<ListNameData>? = null
    private var inspectionFlowAdapter: InspectionFlowAdapter? = null
    private var appBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityInspectionFlowBinding? = null
    private  var inspectionDataList : ArrayList<InspectionData> = ArrayList()
    private  var uploadImageDataList : ArrayList<UploadImageData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInspectionFlowBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
        fetchData()
        setClickListener()
    }

    private fun fetchData() {
        try {
            binding?.progerssDialog?.visibility = View.VISIBLE
            val call: Call<ListDataNamesValueResponse> = ApiClient.getClient.getListOfValue(SharedPrefrenceHelper.getUser(this)?.App_Token)

            call.enqueue(object : Callback<ListDataNamesValueResponse> {

                override fun onResponse(
                    call: Call<ListDataNamesValueResponse>,
                    response: Response<ListDataNamesValueResponse>
                ) {
                    binding?.progerssDialog?.visibility = View.GONE
                    response?.body()?.LIST_NAME.let {
                        if (!it?.isEmpty()!!) {
                            listNameDatalist = it
                            launch {
                                InspectionDatabase.getInstance(this@InspectionFlowActivity).listNameDataDao.insertAll(
                                    it
                                )
                            }
                            getIntentData()
                            // attach adapter to the recycler view
                        }
                    }
                }

                override fun onFailure(call: Call<ListDataNamesValueResponse>, t: Throwable) {
                    binding?.progerssDialog?.visibility = View.GONE
                }

            })
        }catch (e:Exception) {
            e.printStackTrace()
        }

    }

    private fun getIntentData() {
        leadId = intent.getIntExtra(LEAD_ID,0)
        launch {
            leadId?.let {
                leadData = InspectionDatabase.getInstance(this@InspectionFlowActivity).leadDataDao.findSingleLeadData(it)
                setupFlow()
            }
        }
    }

    private fun setClickListener() {
        binding?.nextBtn?.setOnClickListener(this)
        binding?.backBtn?.setOnClickListener(this)

    }

    private fun setupFlow() {
        inspectionFlowAdapter = leadData?.let { listNameDatalist?.let { it1 ->
            InspectionFlowAdapter(supportFragmentManager, it,
                it1
            )
        } }
        binding?.viewPager?.adapter = inspectionFlowAdapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.nextBtn ->
                    goToNext()

            R.id.backBtn -> {
                goToBack()
            }

        }


    }

    private fun goToBack() {
        step= step?.minus(1)
        step?.let {
            if (it >= 0) {
                binding?.viewPager?.setCurrentItem(it)
            }else{
                finish()
            }
        }
    }

    private fun goToNext() {
        getData()
    }

    private fun getData() {
        val page : Fragment? = getSupportFragmentManager().findFragmentByTag("android:switcher:"+R.id.viewPager+":"+binding?.viewPager?.getCurrentItem())
        page?.let {
            if(page is FirstFragment)
            {
                val firstFragment = page
                firstFragment.getData()
            }else if(page is UploadImageFragment)
            {
                val uploadImageFragment = page
                uploadImageFragment.getData()
            }
        }
    }

    override fun sendDataToActivity(inspectionData: InspectionData) {
        inspectionDataList.add(inspectionData)
        step = step?.plus(1)
        step?.let {
            if (it < 31) {
                binding?.viewPager?.setCurrentItem(it)
            }
        }
    }
    companion object {
        //constants
        internal const val LEAD_ID = "leadId"
        internal const val EXTRAS_POST_IMAGE_URL = "post_image_url"

        //starter function
        fun getStartIntent(context: Context,leadId: Int): Intent {
            val intent = Intent(context, InspectionFlowActivity::class.java)
                .putExtra(LEAD_ID,leadId)
            return intent
        }
    }

}
