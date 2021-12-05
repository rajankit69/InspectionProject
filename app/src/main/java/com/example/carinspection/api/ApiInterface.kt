package com.example.carinspection.api

import com.example.carinspection.model.LeadResponse
import com.example.carinspection.model.ListDataNamesValueResponse
import com.example.carinspection.model.ListNameData
import com.example.carinspection.model.User
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @POST("Security/iAppAuth")
    fun authenticateUser(@Header("Username")  Username:String?=null,@Header("Password") password:String?=null,@Header("IP_ADDRESS")  IP_ADDRESS:String?=null,@Header("MAC_ADDRESS") MAC_ADDRESS:String?=null,@Header("LATITUDE") LATITUDE:String?=null,@Header("LONGITUDE") LONGITUDE:String?=null,@Header("OS_NAME") OS_NAME:String?=null,@Header("OS_VERSION")  OS_VERSION:String?=null,@Header("BROWSER_NAME") BROWSER_NAME:String?=null,@Header("BROWSER_NAME")  BROWSER_VERSION:String?=null): Call<User>

    @GET("inspection/get-leads")
    fun getListOfLead(@Header("App_Token") authorization: String?=null): Call<LeadResponse>

    @GET("list-name/Values")
    fun getListOfValue(@Header("App_Token") authorization: String?=null): Call<ListDataNamesValueResponse>
}