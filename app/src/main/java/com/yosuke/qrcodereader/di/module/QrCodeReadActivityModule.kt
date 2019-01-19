package com.yosuke.qrcodereader.di.module

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.yosuke.qrcodereader.di.ViewModelKey
import com.yosuke.qrcodereader.reader.QrCodeReadActivity
import com.yosuke.qrcodereader.reader.QrCodeReadViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface QrCodeReadActivityModule {
    @ContributesAndroidInjector(modules = [QrCodeReadViewModelModule::class])
    fun contributeQrCodeReadActivity(): QrCodeReadActivity
}

@Module
abstract class QrCodeReadViewModelModule {
    @Binds
    abstract fun providesAppCompatActivity(qrCodeReadActivity: QrCodeReadActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ViewModelKey(QrCodeReadViewModel::class)
    abstract fun provideQrCodeReadViewModel(viewModel: QrCodeReadViewModel): ViewModel
}