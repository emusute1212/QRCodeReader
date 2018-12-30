package com.yosuke.qrcodereader.di.module

import android.arch.lifecycle.ViewModel
import com.yosuke.qrcodereader.di.ViewModelKey
import com.yosuke.qrcodereader.home.HomeViewModel
import com.yosuke.qrcodereader.home.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {
    @ContributesAndroidInjector(modules = [MainViewModelModule::class])
    fun contributeMainActivity(): MainActivity
}

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel
}