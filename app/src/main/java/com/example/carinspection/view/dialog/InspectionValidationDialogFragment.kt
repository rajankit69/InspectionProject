package com.example.carinspection.view.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.carinspection.R
import com.example.carinspection.databinding.FragmentInspectionValidationDialogBinding
import com.example.carinspection.databinding.SignupLayoutBinding
import com.example.carinspection.util.SharedPrefrenceHelper
import com.example.carinspection.view.activity.InsceptionListActivity
import com.example.carinspection.view.activity.InspectionFlowActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InspectionValidationDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InspectionValidationDialogFragment(var leadId:Int,var validationId : Int) : DialogFragment() {
    private var fragmentInspectionValidationDialogBinding : FragmentInspectionValidationDialogBinding?=null

    companion object {

        const val TAG = "SimpleDialog"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

/*
        fun newInstance(title: String, subTitle: String): InspectionValidationDialogFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = InspectionValidationDialogFragment(leadId)
            fragment.arguments = args
            return fragment
        }
*/

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentInspectionValidationDialogBinding = FragmentInspectionValidationDialogBinding.inflate(inflater, container, false)
        return fragmentInspectionValidationDialogBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView(view: View) {
        fragmentInspectionValidationDialogBinding?.edtTextPin?.requestFocus();
    }

    private fun setupClickListeners(view: View) {
       fragmentInspectionValidationDialogBinding?.btnNegative?.setOnClickListener {
            dismiss()
        }
        fragmentInspectionValidationDialogBinding?.btnPositive?.setOnClickListener {
            if(validationId==fragmentInspectionValidationDialogBinding?.edtTextPin?.text?.toString()?.toInt())
            {
                dismiss()
                context?.let { startActivity(InspectionFlowActivity.getStartIntent(it,leadId)) }
            }
        }
    }

}