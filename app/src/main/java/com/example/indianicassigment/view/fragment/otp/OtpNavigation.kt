package com.example.indianicassigment.view.fragment.otp

import com.example.indianicassigment.view.base.BaseNavigator


interface OtpNavigation : BaseNavigator {
    fun isError(errorTitle: String, errorMessage: String)

    fun isError(error: String)

    fun isSuccess(tag: Int, message :String)


}