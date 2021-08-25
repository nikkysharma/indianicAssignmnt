package com.example.indianicassigment.view.fragment.wallet_fragment


import com.example.indianicassigment.R
import com.example.indianicassigment.data.model.api.MaxtraResponse
import com.example.indianicassigment.view.base.BaseRVAdapter
import com.example.projectsetwithmvvmdagger.view.navigator.HashListNavigator

import com.example.indianicassigment.BR

class HaskListAdapterTwo (datas:MutableList<MaxtraResponse>, listNavigator: HashListNavigator):
    BaseRVAdapter<MaxtraResponse, HashItemListViewModel>(datas) {

    private val navigator = listNavigator

    override fun getLayoutId() = R.layout.hash_tag_list
    override fun getVariableId() = BR.hashItemViewmodel
    override fun getViewModel(): HashItemListViewModel {
        val viewModel = HashItemListViewModel()
        viewModel.setNavigator(navigator)


        return viewModel
    }
}