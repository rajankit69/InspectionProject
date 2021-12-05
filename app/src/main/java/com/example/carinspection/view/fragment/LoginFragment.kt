package com.example.carinspection.view.fragment

import ForgotPasswordFragment
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.XmlResourceParser
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.carinspection.R
import com.example.carinspection.api.ApiClient
import com.example.carinspection.databinding.LoginLayoutBinding
import com.example.carinspection.model.User
import com.example.carinspection.util.AppHelper
import com.example.carinspection.util.CustomToast
import com.example.carinspection.util.SharedPrefrenceHelper
import com.example.carinspection.util.Utils
import com.example.carinspection.view.activity.InsceptionListActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LoginFragment : Fragment(), View.OnClickListener {
    private var getPin: String = ""
    private var getPassword: String = ""
    private var getUserName: String = ""
    private val hideHandler = Handler()
    private var viewdata: View? = null
    @Suppress("InlinedApi")
    private val hidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        val flags =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        activity?.window?.decorView?.systemUiVisibility = flags
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }
    private val showPart2Runnable = Runnable {
        // Delayed display of UI elements
        fullscreenContentControls?.visibility = View.VISIBLE
    }
    private var visible: Boolean = false
    private val hideRunnable = Runnable { hide() }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val delayHideTouchListener = View.OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    private var dummyButton: Button? = null
    private var fullscreenContent: View? = null
    private var fullscreenContentControls: View? = null

    private var loginLayoutBinding: LoginLayoutBinding? = null


    private var shakeAnimation: Animation? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        loginLayoutBinding = LoginLayoutBinding.inflate(inflater, container, false)
        initViews()
        setListeners()
        viewdata = loginLayoutBinding?.root
        return viewdata
    }

    // Initiate Views
    @SuppressLint("ResourceType")
    private fun initViews() {


        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(
            getActivity(),
            R.anim.shake
        )

        // Setting text selector over textviews
        val xrp: XmlResourceParser = getResources().getXml(R.drawable.text_selector)
        try {
            val csl = ColorStateList.createFromXml(
                getResources(),
                xrp
            )
            loginLayoutBinding?.forgotPassword?.setTextColor(csl)
            loginLayoutBinding?.showHidePassword?.setTextColor(csl)
            loginLayoutBinding?.createAccount?.setTextColor(csl)
        } catch (e: Exception) {
        }
    }

    // Set Listeners
    private fun setListeners() {
        loginLayoutBinding?.loginBtn?.setOnClickListener(this)
        loginLayoutBinding?.forgotPassword?.setOnClickListener(this)
        loginLayoutBinding?.createAccount?.setOnClickListener(this)

        // Set check listener over checkbox for showing and hiding password
        loginLayoutBinding?.showHidePassword?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { button, isChecked ->
            // If it is checkec then show password else hide
            // password
            if (isChecked) {
                loginLayoutBinding?.showHidePassword?.setText(R.string.hide_pwd) // change
                // checkbox
                // text
                loginLayoutBinding?.loginPassword?.inputType = InputType.TYPE_CLASS_TEXT
                loginLayoutBinding?.loginPassword?.transformationMethod =
                    HideReturnsTransformationMethod
                        .getInstance() // show password
            } else {
                loginLayoutBinding?.showHidePassword?.setText(R.string.show_pwd) // change
                // checkbox
                // text
                loginLayoutBinding?.loginPassword?.inputType = (InputType.TYPE_CLASS_TEXT
                        or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                loginLayoutBinding?.loginPassword?.transformationMethod =
                    PasswordTransformationMethod
                        .getInstance() // hide password
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.loginBtn ->
                if (checkValidation()) {
                    goForLogin()
                }
            R.id.forgot_password ->
                // Replace forgot password fragment with animation
                fragmentManager?.beginTransaction()
                    ?.setCustomAnimations(R.anim.right_enter, R.anim.left_out)?.replace(
                        R.id.frameContainer,
                        ForgotPasswordFragment(),
                        Utils.ForgotPassword_Fragment
                    )?.commit()
            R.id.createAccount ->
                // Replace signup frgament with animation
                fragmentManager?.beginTransaction()
                    ?.setCustomAnimations(R.anim.right_enter, R.anim.left_out)?.replace(
                        R.id.frameContainer, SignUpFragment(), Utils.SignUp_Fragment
                    )?.commit()
        }
    }

    private fun goForLogin() = try {
        loginLayoutBinding?.progerssDialog?.visibility = View.VISIBLE
        val call: Call<User> = ApiClient.getClient.authenticateUser(getUserName, getPassword , if (AppHelper.getMACAddress("wlan0")=="") null else AppHelper.getMACAddress("wlan0"),AppHelper.getIPAddress(true),SharedPrefrenceHelper.getLatitude(context),SharedPrefrenceHelper.getLongitude(context),AppHelper.osName(),AppHelper.osVersionName().toString(),null,null)

        call.enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                loginLayoutBinding?.progerssDialog?.visibility = View.GONE
                response?.body()?.let {
                    if(it.Response_Code==200L && it.Response_Message.equals("OK")) {
                        context?.let { it1 ->
                            SharedPrefrenceHelper.saveUser(it, it1)
                            SharedPrefrenceHelper.setScreenPin(getPin, it1)
                            context?.let { startActivity(InsceptionListActivity.getStartIntent(it)) }
                            activity?.finish()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                loginLayoutBinding?.progerssDialog?.visibility = View.GONE
            }

        })
    }catch (e:Exception) {
        e.printStackTrace()
    }

    // Check Validation before login
    private fun checkValidation(): Boolean {
        var isValid: Boolean = true
        // Get email id and password
        getUserName = loginLayoutBinding?.loginEmailid?.text.toString()
        getPassword = loginLayoutBinding?.loginPassword?.text.toString()
        getPin = loginLayoutBinding?.loginPin?.text.toString()
        // Check patter for email id
        /* val p = Pattern.compile(Utils.regEx)
        val m = p.matcher(getEmailId)*/

        // Check for both field is empty or not
       /* if (getUserName == "" || getUserName.length == 0) {
            loginLayoutBinding?.loginLayout?.startAnimation(shakeAnimation)
            getActivity()?.let {
                viewdata?.let { it1 ->
                    CustomToast().Show_Toast(
                        it, it1,
                        "Please enter user name."
                    )
                }
                isValid = false
            }
        } else if (getPassword == "" || getPassword.length == 0) {
            loginLayoutBinding?.loginLayout?.startAnimation(shakeAnimation)
            getActivity()?.let {
                viewdata?.let { it1 ->
                    CustomToast().Show_Toast(
                        it, it1,
                        "Please enter user password."
                    )
                }
                isValid = false
            }
        } else if (getPin == "" || getPin.length == 0) {
            loginLayoutBinding?.loginLayout?.startAnimation(shakeAnimation)
            getActivity()?.let {
                viewdata?.let { it1 ->
                    CustomToast().Show_Toast(
                        it, it1,
                        "Please enter user pin."
                    )
                }
                isValid = false
            }

        }*//*else if (!m.find()) getActivity()?.let {
            CustomToast().Show_Toast(
                it, requireView(),
                "Your Email Id is Invalid."
            )
        } else Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT)
            .show()*/
        return isValid
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        visible = true

        // Set up the user interaction to manually show or hide the system UI.
        fullscreenContent?.setOnClickListener { toggle() }

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        dummyButton?.setOnTouchListener(delayHideTouchListener)
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // Clear the systemUiVisibility flag
        activity?.window?.decorView?.systemUiVisibility = 0
        show()
    }

    override fun onDestroy() {
        super.onDestroy()
        dummyButton = null
        fullscreenContent = null
        fullscreenContentControls = null
    }

    private fun toggle() {
        if (visible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        fullscreenContentControls?.visibility = View.GONE
        visible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        hideHandler.removeCallbacks(showPart2Runnable)
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    @Suppress("InlinedApi")
    private fun show() {
        // Show the system bar
        fullscreenContent?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        visible = true

        // Schedule a runnable to display UI elements after a delay
        hideHandler.removeCallbacks(hidePart2Runnable)
        hideHandler.postDelayed(showPart2Runnable, UI_ANIMATION_DELAY.toLong())
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loginLayoutBinding = null
    }
}