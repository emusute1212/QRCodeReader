package com.yosuke.qrcodereader.di.module

import com.yosuke.qrcodereader.QrCodeApp
import com.yosuke.qrcodereader.data.repository.QrCodeDataRepository
import com.yosuke.qrcodereader.data.repository.QrCodeRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {
    @Provides
    @JvmStatic
    fun provideQrCodeRepository(app: QrCodeApp): QrCodeRepository = QrCodeDataRepository(app)
}