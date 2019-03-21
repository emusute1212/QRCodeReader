package com.yosuke.qrcodereader

import com.jakewharton.threetenabp.AndroidThreeTen
import com.yosuke.qrcodereader.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class QrCodeApp : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().app(this).build()
    }
}