package com.example.indianicassigment.app

import android.content.Context
import android.os.Build
import androidx.multidex.MultiDex
import com.example.indianicassigment.di.component.DaggerAppComponent


import dagger.android.AndroidInjector
import dagger.android.DaggerApplication



/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
class MyApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            MultiDex.install(this)
    }

}