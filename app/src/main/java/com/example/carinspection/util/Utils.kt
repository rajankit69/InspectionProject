package com.example.carinspection.util

import com.example.carinspection.view.fragment.PinValidationFragment

object Utils {
    //Email Validation pattern
    val regEx: String? = "[ \"[a-zA-Z0-9\\\\+\\\\.\\\\_\\\\%\\\\-\\\\+]{1,256}\" +\n" +
            "          \"\\\\@\" +\n" +
            "          \"[a-zA-Z0-9][a-zA-Z0-9\\\\-]{0,64}\" +\n" +
            "          \"(\" +\n" +
            "          \"\\\\.\" +\n" +
            "          \"[a-zA-Z0-9][a-zA-Z0-9\\\\-]{0,25}\" +\n" +
            "          \")+\""

    //Fragments Tags
    val Login_Fragment = "Login_Fragment"
    val SignUp_Fragment = "SignUp_Fragment"
    val ForgotPassword_Fragment = "ForgotPassword_Fragment"
    val PinValidation_Fragment = "ForgotPassword_Fragment"

}