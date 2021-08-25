package com.example.indianicassigment.data.remote




import android.content.Context
import com.example.indianicassigment.data.model.api.MaxtraResponse
import com.example.indianicassigment.network.BaseResponse
import com.example.indianicassigment.network.IApi
import com.example.indianicassigment.network.NetworkCall
import com.example.indianicassigment.network.ServiceCallBack
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
class ApiHelper: IApiHelper {


    val networkCall: NetworkCall

    @Inject
    constructor(networkCall: NetworkCall) {
        this.networkCall = networkCall
    }


//
    override fun listServiceKt(jsonObject: JSONObject, serviceCallBack: ServiceCallBack,  context: Context) {
        try {
            val body = RequestBody.create(
                "application/json; charset=utf-8".toMediaTypeOrNull(),
                jsonObject.toString()
            )
            networkCall.serviceCallBack = serviceCallBack
            networkCall.requestTag = IApi.LIST_TAG
            val responceCall = networkCall.getRetrofit(true, false, context)!!.callListService(body)
            responceCall.enqueue(networkCall.requestCallback() as retrofit2.Callback<BaseResponse<List<MaxtraResponse>>>)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

