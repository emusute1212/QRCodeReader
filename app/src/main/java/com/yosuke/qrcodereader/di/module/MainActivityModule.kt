package com.yosuke.qrcodereader.di.module

import com.yosuke.qrcodereader.home.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule{
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}