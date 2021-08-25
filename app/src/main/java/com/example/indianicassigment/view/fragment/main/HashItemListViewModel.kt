package com.example.indianicassigment.view.fragment.wallet_fragment


import android.view.View
import androidx.databinding.Bindable

import com.example.indianicassigment.data.model.api.MaxtraResponse
import com.example.indianicassigment.view.base.ListItemViewModel
import com.example.projectsetwithmvvmdagger.view.navigator.HashListNavigator


class HashItemListViewModel : ListItemViewModel<MaxtraResponse>() {

    private lateinit var maxtra: MaxtraResponse

    override fun getItem() = maxtra
    override fun setItem(item: MaxtraResponse) {
        this.maxtra = item
        notifyChange()
    }

    @Bindable
    fun getPersonName() = maxtra.name
//
    @Bindable
    fun getImagessUrl():String = maxtra.path +maxtra.userImage

    @Bindable
    fun getNeedUrl():String = "Help Needed For"+maxtra.helpNeededFor

    @Bindable
    fun getLocation():String = "Location :"+maxtra.locationAddress
    @Bindable
    fun getOnClick(): View.OnClickListener{
        return View.OnClickListener {
            (getNavigator() as HashListNavigator).clickedItem(maxtra.id.toString())

        }
    }



}