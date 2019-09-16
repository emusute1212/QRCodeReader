package com.yosuke.qrcodereader.di

import com.yosuke.qrcodereader.QrCodeApp
import com.yosuke.qrcodereader.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    MainActivityModule::class,
    QrCodeReadActivityModule::class,
    GeneratorActivityModule::class,
    QrCodeReaderFromPictureActivityModule::class,
    RepositoryModule::class,
    UsecaseModule::class,
    ViewModelModule::class
])
interface ApplicationComponent : AndroidInjector<QrCodeApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: QrCodeApp): Builder

        fun build(): ApplicationComponent
    }
}