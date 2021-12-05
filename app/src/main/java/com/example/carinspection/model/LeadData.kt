package com.example.carinspection.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "leads_table")
data class LeadData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ID")
    @SerializedName("ID") var iD : Int =0,
    @ColumnInfo(name = "DOE")
    @SerializedName("DOE") var dOE : String?=null,
    @ColumnInfo(name = "DOLU")
    @SerializedName("DOLU") var dOLU : String?=null,
    @ColumnInfo(name = "USER_ID_E")
    @SerializedName("USER_ID_E") var uSER_ID_E : Int=0,
    @ColumnInfo(name = "USER_ID_LU")
    @SerializedName("USER_ID_LU") var uSER_ID_LU : Int=0,
    @ColumnInfo(name = "FLOG_ID")
    @SerializedName("FLOG_ID") var fLOG_ID : Int=0,
    @ColumnInfo(name = "LLOG_ID")
    @SerializedName("LLOG_ID") var lLOG_ID : Int=0,
    @ColumnInfo(name = "LEAD_ID")
    @SerializedName("LEAD_ID") var lEAD_ID : Int=0,
    @ColumnInfo(name = "ASSIGNEE_USER_ID")
    @SerializedName("ASSIGNEE_USER_ID") var aSSIGNEE_USER_ID : Int=0,
    @ColumnInfo(name = "VALIDATION_CODE")
    @SerializedName("VALIDATION_CODE") var vALIDATION_CODE : Int=0,
    @ColumnInfo(name = "SCHEDULED_DATE_TIME")
    @SerializedName("SCHEDULED_DATE_TIME") var sCHEDULED_DATE_TIME : String?=null,
    @ColumnInfo(name = "CUSTOMER_NAME")
    @SerializedName("CUSTOMER_NAME") var cUSTOMER_NAME : String?=null,
    @ColumnInfo(name = "EMAIL_ID")
    @SerializedName("EMAIL_ID") var eMAIL_ID : String?=null,
    @ColumnInfo(name = "PHONE_NUMBER")
    @SerializedName("PHONE_NUMBER") var pHONE_NUMBER : String?=null,
    @ColumnInfo(name = "WHATSAPP_NUMBER")
    @SerializedName("WHATSAPP_NUMBER") var wHATSAPP_NUMBER : String?=null,
    @ColumnInfo(name = "REGISTRATION_ID")
    @SerializedName("REGISTRATION_ID") var rEGISTRATION_ID : String?=null,
    @ColumnInfo(name = "MANUFACTURING_DATE")
    @SerializedName("MANUFACTURING_DATE") var mANUFACTURING_DATE : String?=null,
    @ColumnInfo(name = "MAKE_NAME")
    @SerializedName("MAKE_NAME") var mAKE_NAME : String?=null,
    @ColumnInfo(name = "MODEL_NAME")
    @SerializedName("MODEL_NAME") var mODEL_NAME : String?=null,
    @ColumnInfo(name = "ENGINE_NUMBER")
    @SerializedName("ENGINE_NUMBER") var eNGINE_NUMBER : String?=null,
    @ColumnInfo(name = "VIN")
    @SerializedName("VIN") var vIN : String?=null,
    @ColumnInfo(name = "FUEL_TYPE_NAME")
    @SerializedName("FUEL_TYPE_NAME") var fUEL_TYPE_NAME : String?=null,
    @ColumnInfo(name = "COLOUR")
    @SerializedName("COLOUR") var cOLOUR : String?=null,
    @ColumnInfo(name = "INSURANCE_COPY_AVAILABLE")
    @SerializedName("INSURANCE_COPY_AVAILABLE") var iNSURANCE_COPY_AVAILABLE : Int=0,
    @ColumnInfo(name = "SPARE_KEY_AVAILABLE")
    @SerializedName("SPARE_KEY_AVAILABLE") var sPARE_KEY_AVAILABLE : Int=0,
    @ColumnInfo(name = "PIN_CODE")
    @SerializedName("PIN_CODE") var pIN_CODE : Int=0,
    @ColumnInfo(name = "COUNTRY_NAME")
    @SerializedName("COUNTRY_NAME") var cOUNTRY_NAME : String?=null,
    @ColumnInfo(name = "STATE_NAME")
    @SerializedName("STATE_NAME") var sTATE_NAME : String?=null,
    @ColumnInfo(name = "DISTRICT_NAME")
    @SerializedName("DISTRICT_NAME") var dISTRICT_NAME : String?=null,
    @ColumnInfo(name = "CITY_NAME")
    @SerializedName("CITY_NAME") var cITY_NAME : String?=null,
    @ColumnInfo(name = "ADDRESS")
    @SerializedName("ADDRESS") var aDDRESS : String?=null,
    @ColumnInfo(name = "LANDMARK")
    @SerializedName("LANDMARK") var lANDMARK : String?=null,
    @ColumnInfo(name = "OEM_NAME")
    @SerializedName("OEM_NAME") var oEM_NAME : String?=null,
    @ColumnInfo(name = "MODEL")
    @SerializedName("MODEL") var mODEL : String?=null)
