package com.example.indianicassigment.view.fragment.main

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.example.indianicassigment.R
import com.example.indianicassigment.data.manager.DataManager
import com.example.indianicassigment.data.model.api.MaxtraResponse
import com.example.indianicassigment.network.BaseResponse
import com.example.indianicassigment.network.IApi
import com.example.indianicassigment.network.NetworkCall
import com.example.indianicassigment.network.ServiceCallBack
import com.example.indianicassigment.utils.AppToast
import com.example.indianicassigment.utils.Util
import com.example.indianicassigment.view.base.BaseViewModel
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response


class MainViewFragmentViewModel (dataManager: DataManager)
    : BaseViewModel<MainViewFrgNavigator>(dataManager) , ServiceCallBack {

    var maxtraLiveData: MutableLiveData<List<MaxtraResponse>>? = null

    fun listInCall( context: FragmentActivity?): MutableLiveData<List<MaxtraResponse>> {
        if (maxtraLiveData == null) {
            maxtraLiveData = MutableLiveData<List<MaxtraResponse>>()
        }
        if (Util.isOnline(context)) {
            val jsonObject = JSONObject()
            try {
                jsonObject.put("type", 1)


            } catch (e: JSONException) {
                e.printStackTrace()
            }

            if (context != null) {
                mDataManager.listServiceKt(jsonObject, this, context)
            }
        } else {
            AppToast.show(context as Activity, context.getResources().getString(R.string.check_internet), false)
        }
        return maxtraLiveData as MutableLiveData<List<MaxtraResponse>>
    }

    override fun onSuccess(tag: Int, baseResponse: Response<BaseResponse<Any?>>) {
        Util.hideProgress()
        if (tag == IApi.LIST_TAG) {
            if (baseResponse?.body()?.data != null) {
                val responseModel = baseResponse.body()?.data as List<MaxtraResponse>

                var status = 0
                try {
                   // code = baseResponse.code()
                    assert(baseResponse.body() != null)
                   // message = baseResponse.body()?.message!!
                    status = baseResponse.body()?.status!!
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                if ( status==1) {

                    try {
                        maxtraLiveData?.value = responseModel
                        insertPerson(responseModel)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }


                } else {
                    getNavigator()?.isError(baseResponse?.body()?.message!!)
                }


            }
            else {
                getNavigator()?.isError(baseResponse?.body()?.message!!)
            }
        }


    }
    override fun onFail(code: Int, tag: Int, message: String) {
        Util.hideProgress()
        if (message != null) {
            getNavigator()?.isError(message)
        }

    }

    fun insertPerson(person: List<MaxtraResponse>) {
        mDataManager.insertPersonAll(person)
    }
    fun deletePerson() {
        mDataManager.deleteMaxtra()
    }


}