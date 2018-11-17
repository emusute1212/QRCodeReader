package com.yosuke.qrcodereader.home

import android.os.Bundle
import com.yosuke.qrcodereader.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
    }
}