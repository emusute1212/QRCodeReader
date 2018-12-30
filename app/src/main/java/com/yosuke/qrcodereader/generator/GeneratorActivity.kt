package com.yosuke.qrcodereader.generator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

class GeneratorActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        private fun createIntent(context: Context): Intent {
            return Intent(context, GeneratorActivity::class.java)
        }

        fun startActivity(context: Context) {
            val intent = createIntent(context)
            context.startActivity(intent)
        }
    }
}