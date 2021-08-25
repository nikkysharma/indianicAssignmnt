package com.example.indianicassigment.di.module




import com.example.indianicassigment.data.manager.DataManager
import com.example.indianicassigment.view.activity.main.MainActivityViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
@Module
class MainActivityModule {
    @Provides
    fun provideMainViewModel(dataManager: DataManager)
            = MainActivityViewModel(dataManager)


}