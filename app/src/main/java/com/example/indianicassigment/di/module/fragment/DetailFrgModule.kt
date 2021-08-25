package com.example.indianicassigment.di.module.fragment



import com.example.indianicassigment.data.manager.DataManager
import com.example.indianicassigment.view.fragment.detail.DetailFragmentViewModel
import dagger.Module
import dagger.Provides
@Module
class DetailFrgModule {
    @Provides
    fun provideDetailViewModel(dataManager: DataManager)
            = DetailFragmentViewModel(dataManager)
}