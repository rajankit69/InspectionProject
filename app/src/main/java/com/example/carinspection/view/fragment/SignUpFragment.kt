package com.example.carinspection.view.fragment
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.XmlResourceParser
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.carinspection.LoginSignUpActivity
import com.example.carinspection.R
import com.example.carinspection.databinding.SignupLayoutBinding
import com.example.carinspection.util.CustomToast
import com.example.carinspection.util.Utils
import java.util.regex.Pattern

class SignUpFragment : Fragment(), View.OnClickListener {
    private var signupLayoutBinding : SignupLayoutBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signupLayoutBinding = SignupLayoutBinding.inflate(inflater, container, false)
        initViews()
        setListeners()
        return signupLayoutBinding?.root
    }

    // Initialize all views
    @SuppressLint("ResourceType")
    private fun initViews() {


        // Setting text selector over textviews
        val xrp: XmlResourceParser = getResources().getXml(R.drawable.text_selector)
        try {
            val csl = ColorStateList.createFromXml(
                getResources(),
                xrp
            )
            signupLayoutBinding?.alreadyUser?.setTextColor(csl)
            signupLayoutBinding?.termsConditions?.setTextColor(csl)
        } catch (e: Exception) {
        }
    }

    // Set Listeners
    private fun setListeners() {
        signupLayoutBinding?.signUpBtn?.setOnClickListener(this)
        signupLayoutBinding?.alreadyUser?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.signUpBtn ->
                // Call checkValidation method
                checkValidation()
            R.id.already_user ->
                // Replace login fragment
                LoginSignUpActivity().replaceLoginFragment()
        }
    }

    // Check Validation Method
    private fun checkValidation() {

        signupLayoutBinding?.run {
            // Get all edittext texts
            val getFullName = fullName?.text.toString()
            val getEmailId = userEmailId?.text.toString()
            val getMobileNumber = mobileNumber?.text.toString()
            val getLocation = location?.text.toString()
            val getPassword = password?.text.toString()
            val getConfirmPassword = confirmPassword?.text.toString()

            // Pattern match for email id
            val p = Pattern.compile(Utils.regEx)
            val m = p.matcher(getEmailId)

            // Check if all strings are null or not
            if (getFullName == "" || getFullName.length == 0 || getEmailId == "" || getEmailId.length == 0 || getMobileNumber == "" || getMobileNumber.length == 0 || getLocation == "" || getLocation.length == 0 || getPassword == "" || getPassword.length == 0 || getConfirmPassword == "" || getConfirmPassword.length == 0) CustomToast().Show_Toast(
                requireActivity(),
                requireView(),
                "All fields are required."
            ) else if (!m.find()) getActivity()?.let {
                CustomToast().Show_Toast(
                    it,
                    requireView(),
                    "Your Email Id is Invalid."
                )
            } else if (getConfirmPassword != getPassword) getActivity()?.let {
                CustomToast().Show_Toast(
                    it,
                    requireView(),
                    "Both password doesn't match."
                )
            } else if (!termsConditions.isChecked) getActivity()?.let {
                CustomToast().Show_Toast(
                    it,
                    requireView(),
                    "Please select Terms and Conditions."
                )
            } else Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT)
                .show()
        }
    }

}