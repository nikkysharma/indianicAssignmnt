package com.example.indianicassigment.data.manager


import android.content.Context
import com.example.indianicassigment.data.local.db.DbHelper
import com.example.indianicassigment.data.local.db.IDbHelper
import com.example.indianicassigment.data.local.prefs.ISharedPrefsHelper
import com.example.indianicassigment.data.local.prefs.SharedPrefsHelper
import com.example.indianicassigment.data.model.api.MaxtraResponse
import com.example.indianicassigment.data.remote.ApiHelper
import com.example.indianicassigment.data.remote.IApiHelper
import com.example.indianicassigment.network.ServiceCallBack


import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
@Singleton
class DataManager : IDataManager {



    private val context: Context
    private val mSharedPrefsHelper: SharedPrefsHelper
    private val dbHelper: DbHelper
    private val apiHelper: ApiHelper


    @Inject
    constructor(
        context: Context,
        dbHelper: IDbHelper,
        sharedPreHelper: ISharedPrefsHelper,
        apiHelper: IApiHelper,
    ) {
        this.context = context
        this.mSharedPrefsHelper = sharedPreHelper as SharedPrefsHelper
        this.dbHelper = dbHelper as DbHelper
        this.apiHelper = apiHelper as ApiHelper
    }

    override fun getAllMaxtra(): List<MaxtraResponse> =
        dbHelper.getAllMaxtra()


    override fun insertMaxtra(person: MaxtraResponse) {
        dbHelper.insertMaxtra(person)
    }

    override fun insertPersonAll(person: List<MaxtraResponse>) {
        dbHelper.insertPersonAll(person)
    }

    override fun updateMaxtra(person: MaxtraResponse) {
        dbHelper.updateMaxtra(person)
    }

    override fun deleteMaxtra() {
        dbHelper.deleteMaxtra()
    }

    override fun getMaxtra(id: Int): MaxtraResponse {
       return dbHelper.getMaxtra(id)
    }

    //SharedPrefrence
    override fun setAuthToken(accessToken: String) {
        mSharedPrefsHelper.setAuthToken(accessToken)

    }
    override fun getAuthToken(): String {
        return mSharedPrefsHelper.getAuthToken()
    }

    override fun clear() {
        mSharedPrefsHelper.clear()
    }



    override fun setBooleanData(prefKey: String, value: Boolean?) {
        mSharedPrefsHelper.setBooleanData(prefKey, value)
    }

    override fun getBooleanData(prefKey: String): Boolean? {
        return mSharedPrefsHelper.getBooleanData(prefKey)
    }

    override fun setStringData(prefKey: String, value: String?) {
        mSharedPrefsHelper.setStringData(prefKey, value)
    }

    override fun getStringData(prefKey: String): String? {
        return mSharedPrefsHelper.getStringData(prefKey)
    }

    override fun setIntegerData(prefKey: String, value: Int?) {
        mSharedPrefsHelper.setIntegerData(prefKey, value)
    }

    override fun getIntegerData(prefKey: String): Int? {
        return mSharedPrefsHelper.getIntegerData(prefKey)
    }



    override fun listServiceKt(
        jsonObject: JSONObject,
        serviceCallBack: ServiceCallBack,
        context: Context
    ) {
        apiHelper.listServiceKt(jsonObject, serviceCallBack, context)
    }


}