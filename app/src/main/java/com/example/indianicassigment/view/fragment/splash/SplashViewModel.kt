package com.example.indianicassigment.view.fragment.splash

import android.os.Handler

import androidx.lifecycle.MutableLiveData
import com.example.indianicassigment.data.manager.DataManager

import com.example.indianicassigment.view.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashViewModel (dataManager: DataManager)
    : BaseViewModel<SplashNavigator>(dataManager){
    private var mPageText = ""

    private var mFavs: MutableLiveData<String>? = null
    internal fun startSeeding() {
        Handler().postDelayed({
            //getNavigator()!!.openLoginFragment()
            mFavs?.value = ""
             }, 2000)

//        runBlocking {
//            delay(2000L)
//            mFavs?.value = ""
//        }


    }
    fun updateProfilePic(): MutableLiveData<String> {

        if (mFavs == null) {
            mFavs = MutableLiveData<String>()
        }

        startSeeding()

        return mFavs as MutableLiveData<String>
    }


}