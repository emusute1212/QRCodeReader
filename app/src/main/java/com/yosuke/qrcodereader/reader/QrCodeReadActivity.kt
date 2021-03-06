package com.yosuke.qrcodereader.reader

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
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
                //画面の向きの固定
                it.setOrientationLocked(true)
                //ビープ音の削除
                it.setBeepEnabled(false)
                //activityの指定
                it.captureActivity = CaptureActivityAnyOrientation::class.java
                it.initiateScan()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_CANCELED || data == null) {
            finish()
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            return
        }
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result == null || result.contents == null) {
            finish()
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            return
        }
        ViewModelProviders.of(this, viewModelFactory).get(QrCodeReadViewModel::class.java).setReadText(result.contents)
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