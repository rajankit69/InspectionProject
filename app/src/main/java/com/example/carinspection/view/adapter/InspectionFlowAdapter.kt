package com.example.carinspection.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.carinspection.model.LeadData
import com.example.carinspection.model.ListNameData
import com.example.carinspection.view.fragment.FirstFragment
import com.example.carinspection.view.fragment.ListNameDataFragment
import com.example.carinspection.view.fragment.ReviewFragment
import com.example.carinspection.view.fragment.UploadImageFragment

class InspectionFlowAdapter(fragmentManager: FragmentManager,var leadData: LeadData,var listNameDataList: List<ListNameData>):FragmentPagerAdapter(fragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 31
    }

    override fun getItem(position: Int): Fragment {
        when (position)
        {
          0 ->  return FirstFragment.newInstance("Schedule Date and Time",leadData.sCHEDULED_DATE_TIME, "String",0,true)
            1 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",1,true)
            2 ->  return FirstFragment.newInstance("Phone Number",leadData.pHONE_NUMBER, "String",2,true)
            3 ->  return FirstFragment.newInstance("Email Id",leadData.eMAIL_ID, "String",3,true)
            4 ->  return FirstFragment.newInstance("Whatsapp Number",leadData.wHATSAPP_NUMBER, "String",4,true)
            5 ->  return FirstFragment.newInstance("Registration Id",leadData.eMAIL_ID, "String",5,true)
            6 ->  return FirstFragment.newInstance("Fuel Type Name",leadData.fUEL_TYPE_NAME, "String",6,true)
            7 ->  return FirstFragment.newInstance("Colour",leadData.cOLOUR, "String",7,true)
            8 ->  return FirstFragment.newInstance("Current Odometer Reading",null, "String",8,true)
            9 ->  return FirstFragment.newInstance("Address",leadData.aDDRESS, "String",9,false)
            10 ->  return FirstFragment.newInstance("Area","area", "String",10,false)
            11 ->  return FirstFragment.newInstance("Landmark",leadData.lANDMARK, "String",11,false)
            12 ->  return FirstFragment.newInstance("City",leadData.cITY_NAME, "String",12,false)
            13 ->  return FirstFragment.newInstance("District",leadData.dISTRICT_NAME, "String",13,false)
            14 ->  return FirstFragment.newInstance("State",leadData.sTATE_NAME, "String",14,false)
            15 ->  return FirstFragment.newInstance("Pincode",leadData.pIN_CODE.toString(), "String",15,false)
            16 ->  return FirstFragment.newInstance("Country",leadData.cOUNTRY_NAME, "String",16,false)
            17 ->  return UploadImageFragment.newInstance("RC",leadData.rEGISTRATION_ID, "Registration Number",17,"")
            18 ->  return UploadImageFragment.newInstance("Vin Plate",null, null,18,"")
            19 ->  return UploadImageFragment.newInstance("Odometer",null, null,19,"")
            20 ->  return UploadImageFragment.newInstance("Insurance Policy",null, null,20,"")
            21 ->  return UploadImageFragment.newInstance("AC Gauge Cluster",null, null,21,"")
            22 ->  return UploadImageFragment.newInstance("Ac Compressor",null, null,22,"")
            23 ->  return UploadImageFragment.newInstance("Front Shocker",null, null,23,"")
            24 ->  return UploadImageFragment.newInstance("Rear Shocker",null, null,24,"")
            25 ->  return UploadImageFragment.newInstance("Sump",null, null,25,"")
            26 ->  return ListNameDataFragment.newInstance(null,null, "ListName",26,false,0)
            27 ->  return ListNameDataFragment.newInstance(null,null, "ListName",26,false,1)
            28 ->  return ListNameDataFragment.newInstance(null,null, "ListName",26,false,2)
            29 ->  return ListNameDataFragment.newInstance(null,null, "ListName",26,false,3)
            30 ->  return ListNameDataFragment.newInstance(null,null, "ListName",26,false,4)
            31 ->  return ListNameDataFragment.newInstance(null,null, "ListName",26,false,5)
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