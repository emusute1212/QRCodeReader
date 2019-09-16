package com.yosuke.qrcodereader.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.yosuke.qrcodereader.di.ViewModelKey
import com.yosuke.qrcodereader.picture.QrCodeReaderFromPictureActivity
import com.yosuke.qrcodereader.picture.QrCodeReaderFromPictureViewModel
import com.yosuke.qrcodereader.reader.QrCodeReadViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface QrCodeReaderFromPictureActivityModule {
    @ContributesAndroidInjector(modules = [QrCodeReaderFromPictureViewModelModule::class])
    fun contributeQrCodeReaderFromPictureActivity(): QrCodeReaderFromPictureActivity
}

@Module
abstract class QrCodeReaderFromPictureViewModelModule {
    @Binds
    abstract fun providesAppCompatActivity(qrCodeReaderFromPictureActivity: QrCodeReaderFromPictureActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ViewModelKey(QrCodeReadViewModel::class)
    abstract fun provideQrCodeReadViewModel(viewModel: QrCodeReadViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QrCodeReaderFromPictureViewModel::class)
    abstract fun provideQrCodeReaderFromPictureViewModel(viewModel: QrCodeReaderFromPictureViewModel): ViewModel
}