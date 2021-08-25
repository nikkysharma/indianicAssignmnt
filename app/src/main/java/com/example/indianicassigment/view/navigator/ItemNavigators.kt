package com.example.projectsetwithmvvmdagger.view.navigator

import android.view.View
import com.example.indianicassigment.data.model.BaseModel
import com.example.indianicassigment.view.base.BaseNavigator


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
interface PersonListNavigator : BaseNavigator {
    fun longClickedItem(item: BaseModel)
}
interface HashListNavigator : BaseNavigator {
    fun clickedItem(id :String)
}
interface RemoteItemNavigator: BaseNavigator {
    fun onRemoteItemClick(item: BaseModel, view:View?)
}
interface GalleryItemNavigator: BaseNavigator


