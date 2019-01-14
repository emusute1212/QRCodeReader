package com.yosuke.qrcodereader.di.module

import android.arch.lifecycle.ViewModel
import com.yosuke.qrcodereader.di.ViewModelKey
import com.yosuke.qrcodereader.generator.GeneratorActivity
import com.yosuke.qrcodereader.generator.GeneratorViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface GeneratorActivityModule {
    @ContributesAndroidInjector(modules = [
        GeneratorViewModelModule::class,
        QrCodeViewDialogFragmentModule::class
    ])
    fun contoributeGeneratorActivity(): GeneratorActivity
}

@Module
abstract class GeneratorViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GeneratorViewModel::class)
    abstract fun provideGeneratorViewModel(viewModel: GeneratorViewModel): ViewModel
}