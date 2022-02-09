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

    @PUT("open-api-catalog/Inspection")
    fun uploadNonBlobData(@Header("TRANS_OIL_LEAK ") TRANS_OIL_LEAK : Int?=0,
                          @Header("TRANS_OIL_LEAK_REMARK") TRANS_OIL_LEAK_REMARK : String?="na",
                          @Header("TRANS_EXCESS_NOISE") TRANS_EXCESS_NOISE : Int?=0,
                          @Header("TRANS_EXCESS_NOISE_REMARK") TRANS_EXCESS_NOISE_REMARK  : String?="na",
                          @Header("UNDER_STEER_BALL_JOINT") UNDER_STEER_BALL_JOINT  : Int?=0,
                          @Header("OIL_FLUID_MAIN_OIL_SEAL") OIL_FLUID_MAIN_OIL_SEAL  : Int?=0,
                          @Header("OIL_FLUID_MAIN_OIL_SEAL_REMARK") OIL_FLUID_MAIN_OIL_SEAL_REMARK : String?="na",
                          @Header("OIL_FLUID_COOLANT_LEAK") OIL_FLUID_COOLANT_LEAK   : Int?=0,
                          @Header("ENGINE_CONDENSOR_DAMAGE_CHECK") ENGINE_CONDENSOR_DAMAGE_CHECK    : Int?=0,
                          @Header("ENGINE_CONDENSOR_DAMAGE_CHECK_REMARK") ENGINE_CONDENSOR_DAMAGE_CHECK_REMARK  : String?="na",
                          @Header("ENGINE_SOUND") ENGINE_SOUND    : Int?=0,
                          @Header("ENGINE_SOUND_REMARK") ENGINE_SOUND_REMARK   : String?="na",
                          @Header("ENGINE_BELT_CRACK_HARD_CHECK") ENGINE_BELT_CRACK_HARD_CHECK   : Int?=0,
                          @Header("ENGINE_BELT_CRACK_HARD_CHECK_REMARK") ENGINE_BELT_CRACK_HARD_CHECK_REMARK  : String?="na",
                          @Header("STEER_ASSY_DAMAGE_FLUID_LEAK_CHECK") STEER_ASSY_DAMAGE_FLUID_LEAK_CHECK : Int?=0,
                          @Header("STEER_ASSY_DAMAGE_FLUID_LEAK_CHECK_REMARK") STEER_ASSY_DAMAGE_FLUID_LEAK_CHECK_REMARK  : String?="na",
                          @Header("OIL_FLUID_COOLANT_LEAK_REMARK") OIL_FLUID_COOLANT_LEAK_REMARK  : String?="na",
                          @Header("OIL_FLUID_STEERING_ASSY") OIL_FLUID_STEERING_ASSY  :  Int?=0,
                          @Header("OIL_FLUID_STEERING_ASSY_REMARK") OIL_FLUID_STEERING_ASSY_REMARK  : String?="na",
                          @Header("SUBMISSION_DATE_TIME") SUBMISSION_DATE_TIME  : String?="na",
                          @Header("REGISTRATION_ID") REGISTRATION_ID  : String?="na",
                          @Header("BS_SPEED_WHEEL_SENSOR_WORK") BS_SPEED_WHEEL_SENSOR_WORK  : Int?=0,
                          @Header("BS_SPEED_WHEEL_SENSOR_WORK_REMARK") BS_SPEED_WHEEL_SENSOR_WORK_REMARK  : String?="na",
                          @Header("BS_CALIPER_LEAKE_PISTON_MOVE") BS_CALIPER_LEAKE_PISTON_MOVE  : Int?=0,
                          @Header("BS_CALIPER_LEAKE_PISTON_MOVE_REMARK") BS_CALIPER_LEAKE_PISTON_MOVE_REMARK  : String?="na",
                          @Header("BS_M_CYLINDER_ANY_LEAK_PRESSURE_RELEASE_REMARK") BS_M_CYLINDER_ANY_LEAK_PRESSURE_RELEASE_REMARK  : String?="na",
                          @Header("BS_HAND_BRAKE_LEAVER_WORK") BS_HAND_BRAKE_LEAVER_WORK  : Int?=0,
                          @Header("BS_HAND_BRAKE_LEAVER_WORK_REMARK") BS_HAND_BRAKE_LEAVER_WORK_REMARK  : String?="na",
                          @Header("INSURANCE_COPY_AVAILABLE") INSURANCE_COPY_AVAILABLE  : Int?=0,
                          @Header("App_Token") App_Token  : String?="na",
                          @Header("ID") ID  : Int?=0,
                          @Header("OIL_FLUID_DIFFERENTIAL_OIL") OIL_FLUID_DIFFERENTIAL_OIL  : Int?=0,
                          @Header("OIL_FLUID_DIFFERENTIAL_OIL_REMARK") OIL_FLUID_DIFFERENTIAL_OIL_REMARK  : String?="na",
                          @Header("WWS_WIPE_ALL_FUNC_ALL_SPEED_WORK") WWS_WIPE_ALL_FUNC_ALL_SPEED_WORK  :  Int?=0,
                          @Header("WWS_WIPE_ALL_FUNC_ALL_SPEED_WORK_REMARK") WWS_WIPE_ALL_FUNC_ALL_SPEED_WORK_REMARK  : String?="na",
                          @Header("WWS_WIPE_WASHER_MOTOR_WORK") WWS_WIPE_WASHER_MOTOR_WORK : Int?=0,
                          @Header("WWS_WIPE_WASHER_MOTOR_WORK_REMARK") WWS_WIPE_WASHER_MOTOR_WORK_REMARK  : String?="na",
                          @Header("GRILL_TEMPERATURE") GRILL_TEMPERATURE : Int?=0,
                          @Header("AC_IDLE_MASTER_SWITCH_WORK") AC_IDLE_MASTER_SWITCH_WORK  : Int?=0,
                          @Header("ENGINE_TAPPET") ENGINE_TAPPET  : Int?=0,
                          @Header("ENGINE_TAPPET_REMARK") ENGINE_TAPPET_REMARK  : String?="na",
                          @Header("ENGINE_TAPPET_COVER_PACK") ENGINE_TAPPET_COVER_PACK  : Int?=0,
                          @Header("LLOG_ID") LLOG_ID   : Int?=0,
                          @Header("IL_ALL_KEY_ANY_STUCK") IL_ALL_KEY_ANY_STUCK  : Int?=0,
                          @Header("IL_ALL_KEY_ANY_STUCK_REMARK") IL_ALL_KEY_ANY_STUCK_REMARK  : String?="na",
                          @Header("CUSTOMER_NAME") CUSTOMER_NAME  : String?="na",
                          @Header("ENGINE_TAPPET_COVER_PACK_REMARK") ENGINE_TAPPET_COVER_PACK_REMARK  : String?="na",
                          @Header("ENGINE_COOLANT_LEVEL") ENGINE_COOLANT_LEVEL   : Int?=0,
                          @Header("ENGINE_COOLANT_LEVEL_REMARK") ENGINE_COOLANT_LEVEL_REMARK  : String?="na",
                          @Header("ENGINE_BRAKE_FLUID") ENGINE_BRAKE_FLUID  : Int?=0,



                          @Header("ENGINE_STEERING_FLUID") ENGINE_STEERING_FLUID   : Int?=0,
                          @Header("ENGINE_STEERING_FLUID_REMARK") ENGINE_STEERING_FLUID_REMARK   : String?="na",
                          @Header("ENGINE_OIL_LEVEL") ENGINE_OIL_LEVEL   :  Int?=0,
                          @Header("ENGINE_OIL_LEVEL_REMARK") ENGINE_OIL_LEVEL_REMARK   : String?="na",
                          @Header("ENGINE_AC_PIPES") ENGINE_AC_PIPES  : Int?=0,
                          @Header("ENGINE_AC_PIPES_REMARK") ENGINE_AC_PIPES_REMARK   : String?="na",
                          @Header("ENGINE_CONDENSOR_LEAK") ENGINE_CONDENSOR_LEAK  : Int?=0,
                          @Header("ENGINE_CONDENSOR_LEAK_REMARK") ENGINE_CONDENSOR_LEAK_REMARK   : String?="na",
                          @Header("ENGINE_CONDENSOR_MOTOR_WORK") ENGINE_CONDENSOR_MOTOR_WORK   : Int?=0,
                          @Header("ENGINE_CONDENSOR_MOTOR_WORK_REMARK") ENGINE_CONDENSOR_MOTOR_WORK_REMARK  : String?="na",
                          @Header("ENGINE_RADIATOR_FAN") ENGINE_RADIATOR_FAN   : Int?=0,
                          @Header("ENGINE_RADIATOR_FAN_REMARK") ENGINE_RADIATOR_FAN_REMARK      : String?="na",
                          @Header("ENGINE_RADIATOR_DAMAGE_CHECK") ENGINE_RADIATOR_DAMAGE_CHECK   : Int?=0,
                          @Header("ENGINE_RADIATOR_DAMAGE_CHECK_REMARK") ENGINE_RADIATOR_DAMAGE_CHECK_REMARK   : String?="na",
                          @Header("COMPLETION_DATE_TIME") COMPLETION_DATE_TIME   : String?="na",
                          @Header("BS_M_CYLINDER_ANY_LEAK_PRESSURE_RELEASE") BS_M_CYLINDER_ANY_LEAK_PRESSURE_RELEASE   : Int?=0,
                          @Header("EMAIL_ID") EMAIL_ID   : String?="na",
                          @Header("PHONE_NUMBER") PHONE_NUMBER   : String?="na",
                          @Header("FUEL_TYPE_NAME") FUEL_TYPE_NAME : String?="na",


                          @Header("IP_ALL_GAUGE_WORK") IP_ALL_GAUGE_WORK    : Int?=0,
                          @Header("IP_ALL_GAUGE_WORK_REMARK") IP_ALL_GAUGE_WORK_REMARK    : String?="na",
                          @Header("IP_TACHOMETER_WORK") IP_TACHOMETER_WORK    :  Int?=0,
                          @Header("AC_IDLE_MASTER_SWITCH_WORK_REMARK") AC_IDLE_MASTER_SWITCH_WORK_REMARK   : String?="na",
                          @Header("AC_IDLE_CLOW_MOTOR_ALL_SPEED_WORK") AC_IDLE_CLOW_MOTOR_ALL_SPEED_WORK   : Int?=0,
                          @Header("ENGINE_BRAKE_FLUID_REMARK") ENGINE_BRAKE_FLUID_REMARK    : String?="na",
                          @Header("AC_IDLE_CLOW_MOTOR_ALL_SPEED_WORK_REMARK") AC_IDLE_CLOW_MOTOR_ALL_SPEED_WORK_REMARK   : Int?=0,
                          @Header("AC_IDLE_COMPRESSOR_ABNORMAL_NOISE_LEAK") AC_IDLE_COMPRESSOR_ABNORMAL_NOISE_LEAK     : Int?=0,
                          @Header("AC_IDLE_COMPRESSOR_ABNORMAL_NOISE_LEAK_REMARK") AC_IDLE_COMPRESSOR_ABNORMAL_NOISE_LEAK_REMARK : String?="na",
                          @Header("AC_PIPE_LEAK_DAMAGE") AC_PIPE_LEAK_DAMAGE   : Int?=0,
                          @Header("AC_PIPE_LEAK_DAMAGE_REMARK") AC_PIPE_LEAK_DAMAGE_REMARK       : String?="na",
                          @Header("CS_PL_WORK") CS_PL_WORK    : Int?=0,
                          @Header("MANUFACTURING_DATE") MANUFACTURING_DATE    : String?="na",
                          @Header("MAKE_NAME") MAKE_NAME    : String?="na",

                          @Header("WHATSAPP_NUMBER") WHATSAPP_NUMBER     : String?="na",

                          @Header("START_DATE_TIME") START_DATE_TIME     : String?="na",
                          @Header("CURRENT_INSURANCE_COMPANY_NAME") CURRENT_INSURANCE_COMPANY_NAME     : String?="na",

                          @Header("IP_TACHOMETER_WORK_REMARK") IP_TACHOMETER_WORK_REMARK    : String?="na",
                          @Header("IP_MALFUNC_LIGHT_WORK") IP_MALFUNC_LIGHT_WORK    : Int?=0,

                          @Header("IP_MALFUNC_LIGHT_WORK_REMARK") IP_MALFUNC_LIGHT_WORK_REMARK    : String?="na",
                          @Header("CURRENT_INSURANCE_EXPIRY_DATE") CURRENT_INSURANCE_EXPIRY_DATE     : String?="na",
                          @Header("PWG_OPR_M_SWITCH") PWG_OPR_M_SWITCH     :  Int?=0,
                          @Header("PWG_OPR_M_SWITCH_REMARK") PWG_OPR_M_SWITCH_REMARK    : String?="na",
                          @Header("PWG_OPR_I_SWITCH") PWG_OPR_I_SWITCH    : Int?=0,
                          @Header("MODEL_NAME") MODEL_NAME     : String?="na",




                          @Header("VARIENT_NAME") VARIENT_NAME     : String?="na",
                          @Header("ENGINE_NUMBER") ENGINE_NUMBER    : String?="na",

                          @Header("VIN") VIN     : String?="na",
                          @Header("COLOUR") COLOUR      : String?="na",
                          @Header("CURRENT_ODOMETER_READING") CURRENT_ODOMETER_READING      :  Int?=0,
                          @Header("CS_HL_BEAM_WORK") CS_HL_BEAM_WORK        :  Int?=0,
                          @Header("CS_HL_BEAM_WORK_REMARK") CS_HL_BEAM_WORK_REMARK    : String?="na",
                          @Header("CS_FL_WORK") CS_FL_WORK      : String?="na",
                          @Header("CS_FL_WORK_REMARK") CS_FL_WORK_REMARK     : String?="na",
                          @Header("PWG_OPR_I_SWITCH_REMARK") PWG_OPR_I_SWITCH_REMARK : String?="na",

                          @Header("STEER_WHEEL_MOVE_UP_DOWN") STEER_WHEEL_MOVE_UP_DOWN     :  Int?=0,
                          @Header("STEER_WHEEL_MOVE_UP_DOWN_REMARK") STEER_WHEEL_MOVE_UP_DOWN_REMARK      : String?="na",
                          @Header("STEER_WHEEL_FREE_FLOW_MOVE") STEER_WHEEL_FREE_FLOW_MOVE      :  Int?=0,
                          @Header("STEER_WHEEL_FREE_FLOW_MOVE_REMARK") STEER_WHEEL_FREE_FLOW_MOVE_REMARK     : String?="na",
                          @Header("STEER_NOISE_INTENSITY_FROM_PUMP") STEER_NOISE_INTENSITY_FROM_PUMP     : Int?=0,

                          @Header("STEER_NOISE_INTENSITY_FROM_PUMP_REMARK") STEER_NOISE_INTENSITY_FROM_PUMP_REMARK      : String?="na",
                          @Header("SPARE_KEY_AVAILABLE") SPARE_KEY_AVAILABLE      : Int?=0,
                          @Header("UNDER_STEER_BALL_JOINT_REMARK") UNDER_STEER_BALL_JOINT_REMARK     : String?="na",
                          @Header("UNDER_FRONT_SHOCKER_LEAK") UNDER_FRONT_SHOCKER_LEAK         :  Int?=0,
                          @Header("UNDER_FRONT_SHOCKER_LEAK_REMARK") UNDER_FRONT_SHOCKER_LEAK_REMARK     : String?="na",
                          @Header("UNDER_REAR_SHOCKER_LEAK") UNDER_REAR_SHOCKER_LEAK       :  Int?=0,
                          @Header("UNDER_REAR_SHOCKER_LEAK_REMARK") UNDER_REAR_SHOCKER_LEAK_REMARK      : String?="na",
                          @Header("OIL_FLUID_SUMP_CONDITION") OIL_FLUID_SUMP_CONDITION    :  Int?=0,

                          @Header("OIL_FLUID_SUMP_CONDITION_REMARK") OIL_FLUID_SUMP_CONDITION_REMARK      :  Int?=0,
                          @Header("CS_PL_WORK_REMARK") CS_PL_WORK_REMARK       : String?="na",
                          @Header("AC_COMPRESSOR_ON_OFF_WORK") AC_COMPRESSOR_ON_OFF_WORK      : String?="na",
                          @Header("TRANS_GEAR_SHIFT_HARDNESS") TRANS_GEAR_SHIFT_HARDNESS     : Int?=0,
                          @Header("TRANS_GEAR_SHIFT_HARDNESS_REMARK") TRANS_GEAR_SHIFT_HARDNESS_REMARK      : Int?=0,
                          @Header("TRANS_GEAR_SHIFT_EXCESS_PLAY") TRANS_GEAR_SHIFT_EXCESS_PLAY    : Int?=0,
                          @Header("TRANS_GEAR_SHIFT_EXCESS_PLAY_REMARK") TRANS_GEAR_SHIFT_EXCESS_PLAY_REMARK  : String?="na",

















































































































































    ): Call<ListDataNamesValueResponse>

}