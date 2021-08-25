package com.example.indianicassigment.di.module.fragment

import com.example.indianicassigment.data.manager.DataManager
import com.example.indianicassigment.view.fragment.location.LocationViewFragmentViewModel
import dagger.Module
import dagger.Provides


@Module
class LocationFrgModule {
    @Provides
    fun provideLocationViewModel(dataManager: DataManager)
            = LocationViewFragmentViewModel(dataManager)
}