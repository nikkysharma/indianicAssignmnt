package com.example.indianicassigment.di.module.provider

import com.example.indianicassigment.view.fragment.main.MainViewFragment
import com.example.indianicassigment.di.module.fragment.MainFrgModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Module
abstract class MainViewFrgProvider {
    @ContributesAndroidInjector(modules = [(MainFrgModule::class)])
    abstract fun provideMainFragmentFactory(): MainViewFragment
}