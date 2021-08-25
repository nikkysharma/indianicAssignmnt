package com.example.indianicassigment.view.fragment.login

import com.example.indianicassigment.view.base.BaseNavigator


interface LogInFrgNavigator : BaseNavigator {

    fun openOtp()



    fun isError(errorTitle: String, errorMessage: String)

    fun isError(error: String)

    fun isSuccess(tag: Int, message :String)



}