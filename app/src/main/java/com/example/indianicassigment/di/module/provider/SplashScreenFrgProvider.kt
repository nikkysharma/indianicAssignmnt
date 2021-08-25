package com.example.indianicassigment.di.module.provider

import com.example.indianicassigment.view.fragment.splash.SplashFragment
import com.example.indianicassigment.di.module.fragment.SplashScreenFrgModule


import dagger.Module
import dagger.android.ContributesAndroidInjector
@Module
abstract class SplashScreenFrgProvider {
    @ContributesAndroidInjector(modules = [(SplashScreenFrgModule::class)])
    abstract fun provideSplashScreenFragmentFactory(): SplashFragment
}