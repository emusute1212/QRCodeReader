package com.yosuke.qrcodereader.di.module

import com.yosuke.qrcodereader.generator.QrCodeViewDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface QrCodeViewDialogFragmentModule {

    @ContributesAndroidInjector
    fun contributeQrCodeViewDialogFragmentModule(): QrCodeViewDialogFragment
}