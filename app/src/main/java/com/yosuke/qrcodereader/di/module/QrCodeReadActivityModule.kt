package com.yosuke.qrcodereader.di.module

import com.yosuke.qrcodereader.reader.QrCodeReadActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface QrCodeReadActivityModule {
    @ContributesAndroidInjector
    fun contributeQrCodeReadActivity(): QrCodeReadActivity
}