package com.example.indianicassigment.view.fragment.detail


import com.example.indianicassigment.view.base.BaseNavigator

interface DetailFrgNavigator : BaseNavigator {
     fun isError(errorTitle: String, errorMessage: String)

     fun isError(error: String)

     fun isSuccess(tag: Int, message :String)



}