package com.example.indianicassigment.di.module.fragment

import com.example.indianicassigment.data.manager.DataManager
import com.example.indianicassigment.view.fragment.main.MainViewFragment
import com.example.indianicassigment.view.fragment.main.MainViewFragmentViewModel
import com.example.indianicassigment.view.fragment.wallet_fragment.HaskListAdapterTwo
import dagger.Module
import dagger.Provides
@Module
class MainFrgModule {
    @Provides
    fun provideMainViewModel(dataManager: DataManager)
            = MainViewFragmentViewModel(dataManager)

    @Provides
    fun provideHashListAdapter(fragment: MainViewFragment)
            = HaskListAdapterTwo(ArrayList(),fragment)
}