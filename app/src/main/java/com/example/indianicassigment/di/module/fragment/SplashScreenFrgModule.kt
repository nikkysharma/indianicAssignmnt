package com.example.indianicassigment.di.module.fragment



import com.example.indianicassigment.data.manager.DataManager
import com.example.indianicassigment.view.fragment.splash.SplashViewModel
import dagger.Module
import dagger.Provides
@Module
class SplashScreenFrgModule {
    @Provides
    fun provideSplashScreenViewModel(dataManager: DataManager)
            = SplashViewModel(dataManager)
}