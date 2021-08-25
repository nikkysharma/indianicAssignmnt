package com.example.indianicassigment.view.fragment.detail

import com.example.indianicassigment.data.manager.DataManager
import com.example.indianicassigment.data.model.api.MaxtraResponse
import com.example.indianicassigment.network.BaseResponse
import com.example.indianicassigment.network.ServiceCallBack
import com.example.indianicassigment.view.base.BaseViewModel
import retrofit2.Response


class DetailFragmentViewModel (dataManager: DataManager)
    : BaseViewModel<DetailFrgNavigator>(dataManager) {

    private var datas: MaxtraResponse? = null

    fun getMaxtra(id:Int) :MaxtraResponse
        {
            datas= getDataManager().getMaxtra(id);
            return datas as MaxtraResponse
        }



}