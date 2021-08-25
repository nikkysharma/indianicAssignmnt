package com.example.indianicassigment.data.local.prefs



/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
interface ISharedPrefsHelper {




    abstract fun setAuthToken(accessToken: String)
    fun getAuthToken(): String

    abstract fun setBooleanData(prefKey: String, value: Boolean?)
    abstract fun getBooleanData(prefKey: String): Boolean?
    abstract fun setIntegerData(prefKey: String, value: Int?)
    abstract fun getIntegerData(prefKey: String): Int?
    abstract fun setStringData(prefKey: String, value: String?)
    abstract fun getStringData(prefKey: String): String?

    abstract fun clear()



}