package com.yosuke.qrcodereader.generator

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import javax.inject.Inject

class GeneratorViewModel @Inject constructor(
) : ViewModel() {
    val qrCodeStr = ObservableField<String>()
}