package com.example.indianicassigment.view.base

import com.example.indianicassigment.data.manager.DataManager


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
open class BaseViewModel<T>(dataManager: DataManager): ViewModel() {
     val mDataManager: DataManager = dataManager
    private var mNavigator:T? = null
    public fun getDataManager() = mDataManager

    fun setNavigator(navigator:T){
        mNavigator = navigator
    }

    fun getNavigator() = mNavigator
}