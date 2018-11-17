package com.yosuke.qrcodereader.di

import com.yosuke.qrcodereader.QrCodeApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class
])
interface ApplicationComponent : AndroidInjector<QrCodeApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: QrCodeApp): Builder

        fun build(): ApplicationComponent
    }
}