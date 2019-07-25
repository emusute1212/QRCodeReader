package com.yosuke.qrcodereader.di.module

import androidx.lifecycle.ViewModelProvider
import com.yosuke.qrcodereader.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}