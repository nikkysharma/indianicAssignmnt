package com.example.indianicassigment.di.component



import com.example.indianicassigment.app.MyApplication
import com.example.indianicassigment.di.builder.ActivityBuilder
import com.example.indianicassigment.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, AndroidSupportInjectionModule::class, ActivityBuilder::class])
interface AppComponent:AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<MyApplication>()
}