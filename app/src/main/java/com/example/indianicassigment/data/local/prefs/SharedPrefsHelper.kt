package com.example.indianicassigment.data.local.prefs

import android.content.SharedPreferences

import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@Singleton
class SharedPrefsHelper: ISharedPrefsHelper {



    companion object {
         val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
         val PREF_KEY_INSTAGRAM_ACCESS_TOKEN = "PREF_KEY_INSTAGRAM_ACCESS_TOKEN"
         val PREF_KEY_AUTH_TOKEN = "PREF_KEY_AUTH_TOKEN"


         val PREF_KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL"



        val PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME"
        val PREF_KEY_INSTAGRAM_NAME = "PREF_KEY_INSTAGRAM_NAME"
        val PREF_KEY_INSTAGRAM_USER_NAME = "PREF_KEY_INSTAGRAM_USER_NAME"
        val PREF_KEY_INSTAGRAM_USER_ID = "PREF_KEY_INSTAGRAM_USER_ID"
         val PREF_KEY_USER_PASS = "PREF_KEY_USER_PASS"
        val LANGUAGE="LANGUAGE"



    }
    val mSharedPreferences: SharedPreferences

    @Inject
    constructor(mSharedPreferences: SharedPreferences){
        this.mSharedPreferences = mSharedPreferences
    }





    override fun setAuthToken(accessToken: String) {
        mSharedPreferences.edit().putString(PREF_KEY_AUTH_TOKEN, accessToken).apply()
    }
    override fun getAuthToken(): String {
        return mSharedPreferences.getString(PREF_KEY_AUTH_TOKEN, "").toString()
    }
    override fun setStringData(prefKey: String, value: String?) {
        mSharedPreferences.edit().putString(prefKey, value!!).apply()
    }

    override fun getStringData(prefKey: String): String? {
        return mSharedPreferences.getString(prefKey, "")
    }

    override fun setBooleanData(prefKey: String, value: Boolean?) {
        mSharedPreferences.edit().putBoolean(prefKey, value!!).apply()
    }

    override fun getBooleanData(prefKey: String): Boolean? {

        return mSharedPreferences.getBoolean(prefKey, false)
    }

    override fun setIntegerData(prefKey: String, value: Int?) {
        mSharedPreferences.edit().putInt(prefKey, value!!).apply()
    }

    override fun getIntegerData(prefKey: String): Int? {

        return mSharedPreferences.getInt(prefKey, 0)
    }

    override fun clear() {
        mSharedPreferences.edit().clear().apply()
    }



}