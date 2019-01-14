package com.yosuke.qrcodereader.di.module

import com.yosuke.qrcodereader.data.repository.QrCodeDataRepository
import com.yosuke.qrcodereader.data.repository.QrCodeRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideQrCodeRepository(): QrCodeRepository = QrCodeDataRepository()
}