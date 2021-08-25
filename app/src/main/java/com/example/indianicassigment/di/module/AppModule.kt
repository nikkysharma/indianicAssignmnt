package com.example.indianicassigment.di.module

import androidx.room.Room
import android.content.Context
import com.example.indianicassigment.app.MyApplication
import com.example.indianicassigment.data.local.db.AppDatabase
import com.example.indianicassigment.data.local.db.DbHelper
import com.example.indianicassigment.data.local.db.IDbHelper
import com.example.indianicassigment.data.local.prefs.ISharedPrefsHelper
import com.example.indianicassigment.data.local.prefs.SharedPrefsHelper
import com.example.indianicassigment.data.remote.ApiHelper
import com.example.indianicassigment.data.remote.IApiHelper
import com.example.indianicassigment.di.annotation.DatabaseInfo
import com.example.indianicassigment.di.annotation.PreferenceInfo
import com.example.indianicassigment.utils.ConstantData.DB_NAME
import com.example.indianicassigment.utils.ConstantData.DB_VERSION
import com.example.indianicassigment.utils.ConstantData.SHARED_PREF_NAME


import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
@Module
class AppModule() {
    @Singleton
    @Provides
    fun provideContext(application: MyApplication):Context = application

    @DatabaseInfo
    @Provides
    fun provideDatabaseName() = DB_NAME

    @DatabaseInfo
    @Provides
    fun provideDatabaseVersion() = DB_VERSION

    @PreferenceInfo
    @Provides
    fun providePrefName() = SHARED_PREF_NAME

    @Singleton
    @Provides
    fun provideAppDatabase(context:Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideDbHelper(appDbHelper: DbHelper): IDbHelper = appDbHelper

    @Singleton
    @Provides
    fun providePreferencesHelper(appPreferencesHelper: SharedPrefsHelper): ISharedPrefsHelper
            = appPreferencesHelper

    @Singleton
    @Provides
    fun provideSharedPrefs(context:Context) =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)!!

    @Singleton
    @Provides
    fun provideApiHelper(apiHelper: ApiHelper): IApiHelper = apiHelper


}