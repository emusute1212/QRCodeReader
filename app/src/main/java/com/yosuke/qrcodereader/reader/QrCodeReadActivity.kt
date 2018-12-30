package com.yosuke.qrcodereader.reader

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import dagger.android.support.DaggerAppCompatActivity


class QrCodeReadActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IntentIntegrator(this).also {
            it.setOrientationLocked(false)
            it.captureActivity = CaptureActivityAnyOrientation::class.java
            it.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
                ?: return
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
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