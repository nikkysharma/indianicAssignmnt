package com.example.indianicassigment.data.remote

import android.content.Context
import com.example.indianicassigment.network.NetworkCall
import com.example.indianicassigment.network.ServiceCallBack
import org.json.JSONObject


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
interface IApiHelper {

    abstract fun listServiceKt(
        jsonObject: JSONObject,
        serviceCallBack: ServiceCallBack,
        context: Context
    )
}