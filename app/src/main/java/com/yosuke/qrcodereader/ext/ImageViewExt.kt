package com.yosuke.qrcodereader.ext

import androidx.databinding.BindingAdapter
import android.graphics.Bitmap
import android.widget.ImageView

@BindingAdapter("android:setBitmap")
fun ImageView.setBitmap(bitmap: Bitmap?) {
    if (bitmap == null) return
    setImageBitmap(bitmap)
}