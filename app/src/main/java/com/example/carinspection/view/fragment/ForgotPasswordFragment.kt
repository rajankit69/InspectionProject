
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.XmlResourceParser
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.carinspection.LoginSignUpActivity
import com.example.carinspection.R
import com.example.carinspection.databinding.ForgotpasswordLayoutBinding
import com.example.carinspection.util.CustomToast
import com.example.carinspection.util.Utils
import java.util.regex.Pattern

class ForgotPasswordFragment : Fragment(), View.OnClickListener {
    private var forgotpasswordLayoutBinding : ForgotpasswordLayoutBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        forgotpasswordLayoutBinding = ForgotpasswordLayoutBinding.inflate(inflater, container, false)
        initViews()
        setListeners()
        return forgotpasswordLayoutBinding?.root
    }

    // Initialize the views
    @SuppressLint("ResourceType")
    private fun initViews() {

        // Setting text selector over textviews
        val xrp: XmlResourceParser = getResources().getXml(R.drawable.text_selector)
        try {
            val csl = ColorStateList.createFromXml(
                getResources(),
                xrp
            )
            forgotpasswordLayoutBinding?.backToLoginBtn?.setTextColor(csl)
            forgotpasswordLayoutBinding?.forgotButton?.setTextColor(csl)
        } catch (e: Exception) {
        }
    }

    // Set Listeners over buttons
    private fun setListeners() {
        forgotpasswordLayoutBinding?.backToLoginBtn?.setOnClickListener(this)
        forgotpasswordLayoutBinding?.forgotButton?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backToLoginBtn ->
                // Replace Login Fragment on Back Presses
                LoginSignUpActivity().replaceLoginFragment()
            R.id.forgot_button ->
                // Call Submit button task
                submitButtonTask()
        }
    }

    private fun submitButtonTask() {
        val getEmailId = forgotpasswordLayoutBinding?.registeredEmailid?.text.toString()

        // Pattern for email id validation
        val p = Pattern.compile(Utils.regEx)

        // Match the pattern
        val m = p.matcher(getEmailId)

        // First check if email id is not null else show error toast
        if (getEmailId == "" || getEmailId.length == 0) getActivity()?.let {
            CustomToast().Show_Toast(
                it,
                requireView(),
                "Please enter your Email Id."
            )
        } else if (!m.find()) getActivity()?.let {
            CustomToast().Show_Toast(
                it,
                requireView(),
                "Your Email Id is Invalid."
            )
        } else Toast.makeText(
            getActivity(), "Get Forgot Password.",
            Toast.LENGTH_SHORT
        ).show()
    }


}