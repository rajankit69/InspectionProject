package com.example.carinspection.util

import android.content.Context
import android.content.SharedPreferences
import com.example.carinspection.model.User
import com.google.android.gms.location.LocationCallback
import com.google.gson.Gson




object SharedPrefrenceHelper {

    private var preferencesHashMap = HashMap<String, SharedPreferences>()
    private fun getSharedPreferences(preferencesFile: String, context: Context): SharedPreferences {

        var preferences: SharedPreferences? = preferencesHashMap[preferencesFile]
        if (preferences == null) {
            preferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
            preferencesHashMap[preferencesFile] = preferences
        }

        return preferences!!
    }

    private fun getSharedPreferencesEditor(preferencesFiles: String, context: Context): SharedPreferences.Editor {
        val sharedPreferences = getSharedPreferences(preferencesFiles, context)
        return sharedPreferences.edit()
    }

         fun setLatitude(latitude:Double?,context: Context)
        {
            if(context!=null) {
                val edit = getSharedPreferencesEditor(Constants.SHARED_PREFRENCE, context)
                edit.putString("latitude", latitude.toString())
                edit.apply()
            }
        }
         fun setLongitude(longitude:Double?, context: Context) {
            if(context!=null) {
                val edit = getSharedPreferencesEditor(Constants.SHARED_PREFRENCE, context)
                edit.putString("longitude", longitude.toString())
                edit.apply()
            }
        }
      fun getLatitude(context: Context?): String? {
        if (context == null) return null
        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREFRENCE, context)
        return sharedPreferences.getString("latitude", null)
     }
    fun getLongitude(context: Context?): String? {
        if (context == null) return null
        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREFRENCE, context)
        return sharedPreferences.getString("longitude", null)
    }
    fun saveUser(user: User, context: Context)
    {
        if(context!=null) {
            val gson = Gson()
            val json = gson.toJson(user)
            val edit = getSharedPreferencesEditor(Constants.SHARED_PREFRENCE, context)
            edit.putString("user", json)
            edit.apply()
        }
    }
    fun getUser(context: Context?): User? {
        if (context == null) return null
        val gson = Gson()
        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREFRENCE, context)
        val userData = sharedPreferences.getString("user", null)
        val user : User = gson.fromJson(userData, User::class.java)
        return user
    }
    fun setScreenPin(pin:String?,context: Context)
    {
        if(context!=null) {
            val edit = getSharedPreferencesEditor(Constants.SHARED_PREFRENCE, context)
            edit.putString("screen_pin", pin)
            edit.apply()
        }
    }
    fun getScreenPin(context: Context?): String? {
        if (context == null) return null
        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREFRENCE, context)
        return sharedPreferences.getString("screen_pin", null)
    }

}
