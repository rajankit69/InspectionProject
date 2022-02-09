package com.example.carinspection.view.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carinspection.database.InspectionDatabase
import com.example.carinspection.databinding.FragmentListNameDataBinding
import com.example.carinspection.model.InspectionData
import com.example.carinspection.model.ListNameData
import com.example.carinspection.model.ListNameValue
import com.example.carinspection.util.AppHelper
import com.example.carinspection.util.Constants
import com.example.carinspection.view.adapter.AdapterRadioQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [ListNameDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val LIST_DATA_NUMBER = "listDataNumber"

class ListNameDataFragment : BaseFragment(),AdapterRadioQuestion.ClickListnerOnItem {
    private var inspectionData: InspectionData? = null
    private  var listNameDataList: List<ListNameData>?=null
    private  var mContext: Context?=null
    private  var adapterRadioQuestion: AdapterRadioQuestion?=null
    private var listNameValue : ListNameValue?=null
    var fragmentInterfacer: ListNameDataFragmentInterface? = null


    // TODO: Rename and change types of parameters
    private var question: String? = null
    private var answer: String? = null
    private var screenNumber: Int = 0
    private var isEditable: Boolean = false
    private var objectType: String? = null
    private var listDataNumber: Int = 0
    private var inspectionId : String? = null

    private var listNameDataBinding: FragmentListNameDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = it.getString(Constants.QUESTION)
            answer = it.getString(Constants.ANSWER)
            screenNumber = it.getInt(Constants.SCREEN_NUMBER)
            isEditable = it.getBoolean(Constants.IS_EDITABLE)
            objectType = it.getString(Constants.OBJECT_TYPE)
            listDataNumber = it.getInt(LIST_DATA_NUMBER)
            inspectionId = it.getString(Constants.INSPECTION_ID)


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
            listNameDataList = mContext?.let { InspectionDatabase.getInstance(it).listNameDataDao.getAll() }
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
            listNameDataList?.get(listDataNumber)?.LIST_NAME_VALUES?.let {
                AdapterRadioQuestion(
                    activity,
                    it,
                    this@ListNameDataFragment
                )
            }

        // attach adapter to the recycler view
        listNameDataBinding?.rvRadioBtnAns?.layoutManager =
            LinearLayoutManager(
                mContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        listNameDataBinding?.rvRadioBtnAns?.adapter = adapterRadioQuestion
        listNameDataBinding?.txtHeaderName?.text = listNameDataList?.get(listDataNumber)?.LIST_NAME
       listNameDataBinding?.txtSubHeaderName?.text = listNameDataList?.get(listDataNumber)?.LIST_NAME_DESCRIPTION

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
                        isEditable: Boolean,listDataNumber: Int,inspectionId :String) =
            ListNameDataFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.QUESTION, question)
                    putString(Constants.ANSWER, answer)
                    putInt(Constants.SCREEN_NUMBER, screenNumber)
                    putBoolean(Constants.IS_EDITABLE, isEditable)
                    putString(Constants.OBJECT_TYPE, objectType)
                    putInt(LIST_DATA_NUMBER, listDataNumber)
                    putString(Constants.INSPECTION_ID, inspectionId)
                }
            }
    }

    override fun onClickItem(listNameValueData: ListNameValue) {
        listNameValue = listNameValueData
    }
    interface ListNameDataFragmentInterface {

        fun sendDataToActivity(inspectionData: InspectionData)
    }
    fun getData() {
        if (checkValidationManadatory()) {
            launch {
                context?.let {
                    inspectionData?.let { it1 ->
                        InspectionDatabase.getInstance(it).inspectionDataDao.insert(
                            it1
                        )
                    }
                }
            }

            inspectionData?.let { fragmentInterfacer?.sendDataToActivity(it) }
        }
    }

    private fun checkValidationManadatory(): Boolean {
        if(listNameValue?.isSelected == true) {
            inspectionData = inspectionId?.toInt()?.let {
                InspectionData(
                    listNameValue?.LIST_NAME_VALUE,
                    AppHelper.convertToString(listNameValue),
                    screenNumber,
                    Constants.LIST_DATA,
                    isEditable,
                    it
                )
            }
        }else{
            Toast.makeText(context,"Please select value",Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        fragmentInterfacer = context as ListNameDataFragmentInterface

    }
}