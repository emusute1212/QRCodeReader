package com.yosuke.qrcodereader.generator

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.graphics.Bitmap
import javax.inject.Inject


class GeneratorViewModel @Inject constructor(
) : ViewModel() {
    val qrCodeStr = ObservableField<String>()
    val qrCode = ObservableField<Bitmap>()
}