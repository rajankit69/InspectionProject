package com.example.carinspection.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.carinspection.database.InspectionDatabase
import com.example.carinspection.databinding.FragmentFirstBinding
import com.example.carinspection.model.InspectionData
import com.example.carinspection.util.Constants
import com.example.carinspection.util.SharedPrefrenceHelper
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : BaseFragment() {
    private var databinding: FragmentFirstBinding? = null

    // TODO: Rename and change types of parameters
    private var question: String? = null
    private var answer: String? = null
    private var screenNumber: Int? = null
    private var isEditable: Boolean = false
    private var objectType: String? = null
    private var inspectionId: String? = null

    var fragmentInterfacer: FirstFragmentInterface? = null
    var inspectionData: InspectionData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = it.getString(Constants.QUESTION)
            answer = it.getString(Constants.ANSWER)
            screenNumber = it.getInt(Constants.SCREEN_NUMBER)
            isEditable = it.getBoolean(Constants.IS_EDITABLE)
            objectType = it.getString(Constants.OBJECT_TYPE)
            inspectionId = it.getString(Constants.INSPECTION_ID)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding = FragmentFirstBinding.inflate(inflater, container, false)
        return databinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setQuestion()
        setAnswer()
    }

    private fun setAnswer() {
        databinding?.editAnswer?.run {
        if (!answer.isNullOrBlank()) {
            answer.also { setText(it) }
            setHint(question)
        }

            if (isEditable) {
                setFocusable(true);
                setFocusableInTouchMode(true);
                setClickable(true);
                setCursorVisible(true);
                requestFocus()
            } else {
                setFocusable(false);
                setFocusableInTouchMode(false); // user touches widget on phone with touch screen
                setClickable(false);
                setCursorVisible(false); // user navigates with wheel and selects widget
                clearFocus()
            }
        }
    }

    private fun setQuestion() {
        databinding?.quesHeading?.text = question
    }

    fun getData() {
        if (checkValidationManadatory()) {
            if(SharedPrefrenceHelper.isUpdateData(inspectionId,context))
            {
                launch {
                    context?.let {
                        inspectionData?.let { it1 ->
                            InspectionDatabase.getInstance(it).inspectionDataDao.update(
                                it1
                            )
                        }
                    }
                }

            }else {
                launch {
                    context?.let {
                        inspectionData?.let { it1 ->
                            InspectionDatabase.getInstance(it).inspectionDataDao.insert(
                                it1
                            )
                        }
                    }
                }
            }

            inspectionData?.let { fragmentInterfacer?.sendDataToActivity(it) }
        }
    }

    private fun checkValidationManadatory(): Boolean {
        var ismanadatoryData: Boolean = true
        var answer = databinding?.editAnswer?.text.toString()
        if (answer.isNullOrBlank()) {
            Toast.makeText(context, question, Toast.LENGTH_LONG).show()
            return false
        }
        inspectionData = inspectionId?.toInt()?.let {
            InspectionData(question,answer, screenNumber,
                objectType, isEditable, it
            )
        }
        return ismanadatoryData

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
            question: String,
            answer: String?,
            objectType: String,
            screenNumber: Int,
            isEditable: Boolean,
            inspectionId: String
        ) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.QUESTION, question)
                    putString(Constants.ANSWER, answer)
                    putInt(Constants.SCREEN_NUMBER, screenNumber)
                    putBoolean(Constants.IS_EDITABLE, isEditable)
                    putString(Constants.OBJECT_TYPE, objectType)
                    putString(Constants.INSPECTION_ID, inspectionId)

                }
            }
    }

    interface FirstFragmentInterface {

        fun sendDataToActivity(inspectionData: InspectionData)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInterfacer = context as FirstFragmentInterface
    }
}