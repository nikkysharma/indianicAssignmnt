package com.example.indianicassigment.di.module.fragment

import com.example.indianicassigment.data.manager.DataManager
import com.example.indianicassigment.view.fragment.otp.OtpViewModel
import dagger.Module
import dagger.Provides
@Module
class OtpFrgModule {
    @Provides
    fun providerestViewModel(dataManager: DataManager)
            = OtpViewModel(dataManager)
}