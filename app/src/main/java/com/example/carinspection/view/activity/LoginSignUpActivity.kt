package com.example.carinspection


import android.Manifest
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.carinspection.util.Utils
import com.example.carinspection.view.fragment.LoginFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import androidx.core.app.ActivityCompat.requestPermissions

import android.content.Intent

import android.widget.Toast

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.os.Looper

import com.google.android.gms.location.LocationRequest
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import com.google.android.gms.location.LocationResult

import com.google.android.gms.location.LocationCallback
import android.location.LocationManager
import com.example.carinspection.util.SharedPrefrenceHelper
import com.example.carinspection.view.fragment.CameraFragment
import com.example.carinspection.view.fragment.PinValidationFragment
import com.example.carinspection.view.fragment.VideoFragment


class LoginSignUpActivity : AppCompatActivity() {
    // initializing
    // FusedLocationProviderClient
    // object
    var mFusedLocationClient: FusedLocationProviderClient? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var PERMISSION_ID = 44

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_signup)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // method to get the location
        getLastLocation();
        // If savedinstnacestate is null then replace login fragment
        if(SharedPrefrenceHelper.getScreenPin(this)!=null)
        {
            if (savedInstanceState == null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.frameContainer, PinValidationFragment(),
                        Utils.PinValidation_Fragment
                    ).commit()
            }

        }else {
            if (savedInstanceState == null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.frameContainer, LoginFragment(),
                        Utils.Login_Fragment
                    ).commit()
            }
        }

        // On close icon click finish activity
        findViewById<View>(R.id.close_activity).setOnClickListener { finish() }
    }

    // Replace Login Fragment with animation
    public fun replaceLoginFragment() {
        supportFragmentManager?.beginTransaction()?.replace(
            R.id.frameContainer, LoginFragment(),
            Utils.Login_Fragment
        )?.commit()
    }

    override fun onBackPressed() {

        // Find the tag of signup and forgot password fragment
        val SignUp_Fragment: Fragment? = supportFragmentManager?.findFragmentByTag(Utils.SignUp_Fragment)
        val ForgotPasswordFragment: Fragment? = supportFragmentManager?.findFragmentByTag(Utils.ForgotPassword_Fragment)

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task
        if (SignUp_Fragment != null) replaceLoginFragment() else if (ForgotPasswordFragment != null) replaceLoginFragment() else super.onBackPressed()
    }
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient!!.lastLocation.addOnCompleteListener { task ->
                    val location = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        latitude = location.latitude
                        longitude = location.longitude
                        SharedPrefrenceHelper.setLatitude(latitude,this)
                        SharedPrefrenceHelper.setLongitude(longitude,this)
                    }
                }
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG)
                    .show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions()
        }
    }
    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 5
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient?.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()
        )
    }
    private val mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation = locationResult.lastLocation
           latitude =   mLastLocation.latitude
           longitude = mLastLocation.longitude
            SharedPrefrenceHelper.setLatitude(latitude,this@LoginSignUpActivity)
            SharedPrefrenceHelper.setLongitude(longitude,this@LoginSignUpActivity)
        }
    }

    // method to check for permissions
    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private fun requestPermissions() {
        requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), PERMISSION_ID
        )
    }

    // method to check
    // if location is enabled
    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    // If everything is alright then
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (checkPermissions()) {
            getLastLocation()
        }
    }

}