package com.example.indianicassigment.di.module.fragment


import com.example.indianicassigment.data.manager.DataManager
import com.example.indianicassigment.view.fragment.login.LoginFragmentViewModel
import dagger.Module
import dagger.Provides

@Module

class LoginFrgModule {
    @Provides
    fun provideLoginViewModel(dataManager: DataManager)
            = LoginFragmentViewModel(dataManager)
}