package com.yosuke.qrcodereader.di.module

import androidx.lifecycle.ViewModel
import androidx.appcompat.app.AppCompatActivity
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
    abstract fun providesAppCompatActivity(generatorActivity: GeneratorActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ViewModelKey(GeneratorViewModel::class)
    abstract fun provideGeneratorViewModel(viewModel: GeneratorViewModel): ViewModel
}