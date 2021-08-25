package com.example.indianicassigment.di.module.provider

import com.example.indianicassigment.di.module.fragment.LocationFrgModule
import com.example.indianicassigment.view.fragment.location.LocationViewFragment


import dagger.Module
import dagger.android.ContributesAndroidInjector
@Module
abstract class LocationFrgProvider {
    @ContributesAndroidInjector(modules = [(LocationFrgModule::class)])
    abstract fun provideLocationFragmentFactory(): LocationViewFragment
}