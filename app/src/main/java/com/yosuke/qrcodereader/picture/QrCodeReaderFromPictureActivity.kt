package com.yosuke.qrcodereader.picture

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class QrCodeReaderFromPictureActivity:DaggerAppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


}