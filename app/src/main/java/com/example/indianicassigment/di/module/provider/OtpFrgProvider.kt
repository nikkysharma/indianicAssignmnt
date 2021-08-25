package com.example.indianicassigment.di.module.provider

import com.example.indianicassigment.di.module.fragment.OtpFrgModule
import com.example.indianicassigment.view.fragment.otp.OtpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Module
abstract class OtpFrgProvider {

    @ContributesAndroidInjector(modules = [(OtpFrgModule::class)])
    abstract fun providerestFragmentFactory(): OtpFragment
}