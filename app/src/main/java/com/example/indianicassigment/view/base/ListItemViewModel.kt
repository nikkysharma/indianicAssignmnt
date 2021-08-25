package com.example.indianicassigment.view.base



/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
abstract class ListItemViewModel<ITEM>: ViewModel() {
    abstract fun setItem(item:ITEM)
    abstract fun getItem(): ITEM

    private lateinit var mNavigator: BaseNavigator

    fun setNavigator(navigator: BaseNavigator){
        mNavigator = navigator
    }

    fun getNavigator() = mNavigator
}