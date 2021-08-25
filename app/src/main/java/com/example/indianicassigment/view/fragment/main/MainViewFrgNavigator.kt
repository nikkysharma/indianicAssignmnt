package com.example.indianicassigment.view.fragment.main

import com.example.indianicassigment.view.base.BaseNavigator


interface MainViewFrgNavigator : BaseNavigator {



    fun isError(errorTitle: String, errorMessage: String)

    fun isError(error: String)

    fun isSuccess(tag: Int, message :String)


}