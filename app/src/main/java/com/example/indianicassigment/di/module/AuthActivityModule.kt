package com.example.indianicassigment.di.module


import com.example.indianicassigment.data.manager.DataManager
import com.example.indianicassigment.view.activity.auth.AuthActivityViewModel
import dagger.Module
import dagger.Provides
@Module
class AuthActivityModule {
    @Provides
    fun provideAuthViewModel(dataManager: DataManager)
            = AuthActivityViewModel(dataManager)
}