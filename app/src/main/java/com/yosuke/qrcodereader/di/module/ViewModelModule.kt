package com.yosuke.qrcodereader.di.module

import android.arch.lifecycle.ViewModelProvider
import com.yosuke.qrcodereader.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}