package com.yosuke.qrcodereader.generator

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.graphics.Bitmap
import com.yosuke.qrcodereader.data.repository.QrCodeRepository
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


class GeneratorViewModel @Inject constructor(
        private val navigator: GeneratorNavigator,
        private val repository: QrCodeRepository
) : ViewModel() {
    val qrCodeStr = ObservableField<String>()
    val qrCode = ObservableField<Bitmap>()

    fun onGenerateButton() {
        if (qrCodeStr.get().isNullOrEmpty()) return
        launch { qrCode.set(repository.encodeQrCode(requireNotNull(qrCodeStr.get()))) }
        navigator.viewQrCodeDialog()
    }

    fun onSaveButton(image: Bitmap) {
        navigator.save(image)
    }
}