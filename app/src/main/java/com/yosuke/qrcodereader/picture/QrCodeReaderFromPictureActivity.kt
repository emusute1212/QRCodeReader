package com.yosuke.qrcodereader.picture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yosuke.qrcodereader.R
import com.yosuke.qrcodereader.databinding.ActivityQrCodeReadActivityBinding
import com.yosuke.qrcodereader.reader.QrCodeReadViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class QrCodeReaderFromPictureActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: QrCodeReadViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(QrCodeReadViewModel::class.java)
    }
    private val qrCodeReaderFromPictureViewModel: QrCodeReaderFromPictureViewModel by lazy {
        ViewModelProviders
                .of(this, viewModelFactory)
                .get(QrCodeReaderFromPictureViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil
                .setContentView<ActivityQrCodeReadActivityBinding>(
                        this,
                        R.layout.activity_qr_code_read_activity
                )

        binding.viewModel = viewModel

        //画面回転やActivity再生時などはスキャン画面へ移行しない
        if (savedInstanceState == null) {
            Intent(Intent.ACTION_OPEN_DOCUMENT).also { intent ->
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(intent, RESULT_PICK_IMAGEFILE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_CANCELED || data == null) {
            finish()
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            return
        }
        val result = data.data?.let { uri ->
            Log.i(TAG, "Uri: $uri")
            qrCodeReaderFromPictureViewModel.scanQRImage(MediaStore.Images.Media.getBitmap(contentResolver, uri))
        } ?: ""
        viewModel.setReadText(result)
    }

    companion object {
        private val TAG = QrCodeReaderFromPictureActivity::class.java.simpleName
        private const val RESULT_PICK_IMAGEFILE = 42
        private fun createIntent(context: Context): Intent {
            return Intent(context, QrCodeReaderFromPictureActivity::class.java)
        }

        fun startActivity(context: Context) {
            val intent = createIntent(context)
            context.startActivity(intent)
        }
    }
}