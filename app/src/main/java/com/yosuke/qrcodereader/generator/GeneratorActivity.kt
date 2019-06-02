package com.yosuke.qrcodereader.generator

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.yosuke.qrcodereader.R
import com.yosuke.qrcodereader.databinding.ActivityGeneratorBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class GeneratorActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityGeneratorBinding>(this, R.layout.activity_generator)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(GeneratorViewModel::class.java)

        binding.viewModel = viewModel
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