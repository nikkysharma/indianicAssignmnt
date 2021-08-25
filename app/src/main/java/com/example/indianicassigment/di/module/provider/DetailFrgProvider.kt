package com.example.indianicassigment.di.module.provider

import com.example.indianicassigment.view.fragment.detail.DetailFragment
import com.example.indianicassigment.di.module.fragment.DetailFrgModule

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class DetailFrgProvider {
    @ContributesAndroidInjector(modules = [(DetailFrgModule::class)])
    abstract fun provideDetailFragmentFactory(): DetailFragment
}