package com.example.indianicassigment.di.module.provider

import com.example.indianicassigment.di.module.fragment.LoginFrgModule

import com.example.indianicassigment.view.fragment.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginFrgProvider {
    @ContributesAndroidInjector(modules = [(LoginFrgModule::class)])
    abstract fun provideLoginFragmentFactory(): LoginFragment
}