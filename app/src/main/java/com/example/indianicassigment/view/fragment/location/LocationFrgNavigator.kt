package com.example.indianicassigment.view.fragment.location

import com.example.indianicassigment.view.base.BaseNavigator


interface LocationFrgNavigator : BaseNavigator {
    fun isError(errorTitle: String, errorMessage: String)

    fun isError(error: String)

    fun isSuccess(tag: Int, message :String)



}