package com.example.carinspection.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carinspection.R
import com.example.carinspection.database.InspectionDatabase
import com.example.carinspection.databinding.FragmentListNameDataBinding
import com.example.carinspection.databinding.LoginLayoutBinding
import com.example.carinspection.model.LeadData
import com.example.carinspection.model.ListNameData
import com.example.carinspection.view.adapter.AdapterRadioQuestion
import com.example.carinspection.view.adapter.InspectionListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "question"
private const val ARG_PARAM2 = "answer"
private const val ARG_PARAM3 = "screenNumber"
private const val ARG_PARAM4 = "isEditable"
private const val ARG_PARAM5 = "objectType"
private const val ARG_PARAM6 = "listDataNumber"

/**
 * A simple [Fragment] subclass.
 * Use the [ListNameDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListNameDataFragment : BaseFragment(),AdapterRadioQuestion.ClickListnerOnItem {
    private lateinit var listNameDataList: List<ListNameData>
    private lateinit var mContext: Context
    private  var adapterRadioQuestion: AdapterRadioQuestion?=null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: Int = 0
    private var param4: Boolean = false
    private var param5: String? = null
    private var param6: Int = 0

    private var listNameDataBinding: FragmentListNameDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getInt(ARG_PARAM3)
            param4 = it.getBoolean(ARG_PARAM4)
            param5 = it.getString(ARG_PARAM5)
            param6 = it.getInt(ARG_PARAM6)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        listNameDataBinding = FragmentListNameDataBinding.inflate(inflater, container, false)
        setView()
        return listNameDataBinding?.root
    }
    private fun setView() {
        launch {
            listNameDataList = InspectionDatabase.getInstance(mContext).listNameDataDao.getAll()
            CoroutineScope(Dispatchers.Main).launch {
                updateUi()
            }

        }

    }
   /* suspend fun getUserFromDB() {
        listNameDataList = InspectionDatabase.getInstance(mContext).listNameDataDao.getAll().a
        return profileDao.getUserProfile().await()
    }*/
    private fun updateUi() {
        adapterRadioQuestion =
            AdapterRadioQuestion(
                activity,
                listNameDataList.get(param6).LIST_NAME_VALUES,
                this@ListNameDataFragment
            )

        // attach adapter to the recycler view
        listNameDataBinding?.rvRadioBtnAns?.layoutManager =
            LinearLayoutManager(
                mContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        listNameDataBinding?.rvRadioBtnAns?.adapter = adapterRadioQuestion
        listNameDataBinding?.txtHeaderName?.text = listNameDataList?.get(param6)?.LIST_NAME
       listNameDataBinding?.txtSubHeaderName?.text = listNameDataList?.get(param6)?.LIST_NAME_DESCRIPTION

   }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListNameDataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(question: String?,
                        answer: String?,
                        objectType: String,
                        screenNumber: Int,
                        isEditable: Boolean,listDataNumber: Int) =
            ListNameDataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, question)
                    putString(ARG_PARAM2, answer)
                    putInt(ARG_PARAM3, screenNumber)
                    putBoolean(ARG_PARAM4, isEditable)
                    putString(ARG_PARAM5, objectType)
                    putInt(ARG_PARAM6, listDataNumber)
                }
            }
    }

    override fun onClickItem(position: Int) {
        TODO("Not yet implemented")
    }
}