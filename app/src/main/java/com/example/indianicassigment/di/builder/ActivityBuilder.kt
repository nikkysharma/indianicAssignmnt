package com.example.indianicassigment.di.builder


import com.example.indianicassigment.di.module.AuthActivityModule
import com.example.indianicassigment.di.module.MainActivityModule
import com.example.indianicassigment.di.module.provider.*
import com.example.indianicassigment.view.activity.auth.AuthActivity
import com.example.indianicassigment.view.activity.main.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [ (MainActivityModule::class),
        (MainViewFrgProvider::class),
        (DetailFrgProvider::class),
        (LocationFrgProvider::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(AuthActivityModule::class),
        (SplashScreenFrgProvider::class),
        (LoginFrgProvider::class),
        (OtpFrgProvider::class)
       ])
    abstract fun bindAuthActivity(): AuthActivity

}