package com.example.carinspection.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.set
import com.example.carinspection.databinding.FragmentFirstBinding
import com.example.carinspection.model.InspectionData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "question"
private const val ARG_PARAM2 = "answer"
private const val ARG_PARAM3 = "screenNumber"
private const val ARG_PARAM4 = "isEditable"
private const val ARG_PARAM5 = "objectType"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    private var databinding: FragmentFirstBinding? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: Boolean = false
    private var param5: String? = null

    var fragmentInterfacer: FirstFragmentInterface? = null
    var inspectionData: InspectionData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getBoolean(ARG_PARAM4)
            param5 = it.getString(ARG_PARAM5)

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
        if (!param2.isNullOrBlank()) {
            param2.also { setText(it) }
            setHint(param1)
        }

            if (param4) {
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
        databinding?.quesHeading?.text = param1
    }

    fun getData() {
        if (checkValidationManadatory()) {
            inspectionData?.let { fragmentInterfacer?.sendDataToActivity(it) }
        }
    }

    private fun checkValidationManadatory(): Boolean {
        var ismanadatoryData: Boolean = true
        var answer = databinding?.editAnswer?.text.toString()
        if (answer.isNullOrBlank()) {
            Toast.makeText(context, param1, Toast.LENGTH_LONG).show()
            return false
        }
        inspectionData = InspectionData(answer, param3?.toInt(), param5, param4)
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
            isEditable: Boolean
        ) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, question)
                    putString(ARG_PARAM2, answer)
                    putInt(ARG_PARAM3, screenNumber)
                    putBoolean(ARG_PARAM4, isEditable)
                    putString(ARG_PARAM5, objectType)
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