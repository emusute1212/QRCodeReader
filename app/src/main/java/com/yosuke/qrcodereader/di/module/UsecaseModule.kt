package com.yosuke.qrcodereader.di.module

import com.yosuke.qrcodereader.QrCodeApp
import com.yosuke.qrcodereader.data.repository.QrCodeDataRepository
import com.yosuke.qrcodereader.data.repository.QrCodeRepository
import com.yosuke.qrcodereader.usecase.QrCodeUsecase
import dagger.Module
import dagger.Provides

@Module
object UsecaseModule {
    @Provides
    @JvmStatic
    fun provideUsecase(): QrCodeUsecase = QrCodeUsecase()
}