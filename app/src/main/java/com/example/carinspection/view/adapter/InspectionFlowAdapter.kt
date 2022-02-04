package com.example.carinspection.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.carinspection.model.LeadData
import com.example.carinspection.model.ListNameData
import com.example.carinspection.util.Constants.IMAGE
import com.example.carinspection.util.Constants.LIST_DATA
import com.example.carinspection.util.Constants.STRING_DATA
import com.example.carinspection.util.Constants.VIDEO
import com.example.carinspection.view.fragment.*

class InspectionFlowAdapter(fragmentManager: FragmentManager,var leadData: LeadData,var listNameDataList: List<ListNameData>):FragmentPagerAdapter(fragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 34
    }

    override fun getItem(position: Int): Fragment {
        when (position)
        {
            0 ->  return FirstFragment.newInstance("Schedule Date and Time",leadData.sCHEDULED_DATE_TIME, STRING_DATA,0,true)
            1 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, STRING_DATA,1,true)
            2 ->  return FirstFragment.newInstance("Phone Number",leadData.pHONE_NUMBER, STRING_DATA,2,true)
            3 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, STRING_DATA,3,true)
            4 ->  return FirstFragment.newInstance("Whatsapp Number",leadData.wHATSAPP_NUMBER, STRING_DATA,4,true)
            5 ->  return FirstFragment.newInstance("Registration Id",leadData.eMAIL_ID, STRING_DATA,5,true)
            6 ->  return FirstFragment.newInstance("Fuel Type Name",leadData.fUEL_TYPE_NAME, STRING_DATA,6,true)
            7 ->  return FirstFragment.newInstance("Colour",leadData.cOLOUR, STRING_DATA,7,true)
            8 ->  return FirstFragment.newInstance("Current Odometer Reading",null, STRING_DATA,8,true)
            9 ->  return FirstFragment.newInstance("Address",leadData.aDDRESS, STRING_DATA,9,false)
            10 ->  return FirstFragment.newInstance("Area","area", STRING_DATA,10,false)
            11 ->  return FirstFragment.newInstance("Landmark",leadData.lANDMARK, STRING_DATA,11,false)
            12 ->  return FirstFragment.newInstance("City",leadData.cITY_NAME, STRING_DATA,12,false)
            13 ->  return FirstFragment.newInstance("District",leadData.dISTRICT_NAME, STRING_DATA,13,false)
            14 ->  return FirstFragment.newInstance("State",leadData.sTATE_NAME, STRING_DATA,14,false)
            15 ->  return FirstFragment.newInstance("Pincode",leadData.pIN_CODE.toString(), STRING_DATA,15,false)
            16 ->  return FirstFragment.newInstance("Country",leadData.cOUNTRY_NAME, STRING_DATA,16,false)
            17 ->  return UploadImageFragment.newInstance("RC",leadData.rEGISTRATION_ID, "Registration Number",17,IMAGE)
            18 ->  return UploadImageFragment.newInstance("Vin Plate",null, null,18,IMAGE)
            19 ->  return UploadImageFragment.newInstance("Odometer",null, null,19,IMAGE)
            20 ->  return UploadImageFragment.newInstance("Insurance Policy",null, null,20,IMAGE)
            21 ->  return UploadImageFragment.newInstance("AC Gauge Cluster",null, null,21,IMAGE)
            22 ->  return UploadImageFragment.newInstance("Ac Compressor",null, null,22,IMAGE)
            23 ->  return UploadImageFragment.newInstance("Front Shocker",null, null,23,IMAGE)
            24 ->  return UploadImageFragment.newInstance("Rear Shocker",null, null,24,IMAGE)
            25 ->  return UploadImageFragment.newInstance("Sump",null, null,25,IMAGE)
            26 ->  return ListNameDataFragment.newInstance(null,null, LIST_DATA,26,true,0)
            27 ->  return ListNameDataFragment.newInstance(null,null, LIST_DATA,27,true,1)
            28 ->  return ListNameDataFragment.newInstance(null,null, LIST_DATA,28,true,2)
            29 ->  return ListNameDataFragment.newInstance(null,null, LIST_DATA,29,true,3)
            30 ->  return ListNameDataFragment.newInstance(null,null, LIST_DATA,30,true,4)
            31 ->  return ListNameDataFragment.newInstance(null,null, LIST_DATA,31,true,5)
            32 ->  return CameraFragment.newInstance("Exaust Video","","","",32,VIDEO)
            33 ->  return  ReviewFragment.newInstance()
          /*  0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)
            0 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",0)*/
          else-> {
                return ReviewFragment.newInstance()
            }
            }

        }

}