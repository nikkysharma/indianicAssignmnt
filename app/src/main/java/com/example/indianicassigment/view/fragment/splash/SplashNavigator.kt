package com.example.indianicassigment.view.fragment.splash


import com.example.indianicassigment.view.base.BaseNavigator

interface SplashNavigator: BaseNavigator {
    fun isError(errorTitle: String, errorMessage: String)

    fun isError(error: String)

    fun isSuccess(tag: Int, message :String)
    fun openLoginFragment()


}