package com.yosuke.qrcodereader.reader

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import com.yosuke.qrcodereader.R
import com.yosuke.qrcodereader.databinding.ActivityQrCodeReadActivityBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class QrCodeReadActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityQrCodeReadActivityBinding>(this, R.layout.activity_qr_code_read_activity)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(QrCodeReadViewModel::class.java)

        binding.viewModel = viewModel

        //画面回転やActivity再生時などはスキャン画面へ移行しない
        if (savedInstanceState == null) {
            IntentIntegrator(this).also {
                it.setOrientationLocked(false)
                it.captureActivity = CaptureActivityAnyOrientation::class.java
                it.initiateScan()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
                ?: return
        if (result.contents != null) {
            Toast.makeText(this, "Scanned: ${result.contents}", Toast.LENGTH_LONG).show()
            ViewModelProviders.of(this, viewModelFactory).get(QrCodeReadViewModel::class.java).setReadText(result.contents)
        } else {
            finish()
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private fun createIntent(context: Context): Intent {
            return Intent(context, QrCodeReadActivity::class.java)
        }

        fun startActivity(context: Context) {
            val intent = createIntent(context)
            context.startActivity(intent)
        }
    }
}

/**
 * QRコード読み取り画面が横に固定されないようにするために作成したクラス
 * [参考](https://qiita.com/alingogo/items/3006e5685057c23db6bd)
 */
class CaptureActivityAnyOrientation : CaptureActivity()