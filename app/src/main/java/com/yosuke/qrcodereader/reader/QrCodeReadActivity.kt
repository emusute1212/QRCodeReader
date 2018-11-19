package com.yosuke.qrcodereader.reader

import android.content.Context
import android.content.Intent
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.os.Handler
import android.view.Surface
import android.view.TextureView
import com.yosuke.qrcodereader.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_qr_code_read_activity.*
import java.util.*


class QrCodeReadActivity : DaggerAppCompatActivity() {
    private var cameraDevice: CameraDevice? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_read_activity)

        camera.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
                openCamera()
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return true
            }

            override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {}
            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {}
        }

    }

    private fun openCamera() {
        val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            manager.openCamera(manager.cameraIdList[0], object : CameraDevice.StateCallback() {
                override fun onOpened(cameraDevice: CameraDevice) {
                    this@QrCodeReadActivity.cameraDevice = cameraDevice
                    createCameraPreviewSession()
                }

                override fun onDisconnected(cameraDevice: CameraDevice) {
                    cameraDevice.close()
                    this@QrCodeReadActivity.cameraDevice = null
                }

                override fun onError(cameraDevice: CameraDevice, error: Int) {
                    cameraDevice.close()
                    this@QrCodeReadActivity.cameraDevice = null
                }

            }, Handler())
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    private fun createCameraPreviewSession() {
        val texture = camera.surfaceTexture
        texture.setDefaultBufferSize(320, 240) // 自分の手元のデバイスで決めうちしてます
        val surface = Surface(texture)

        try {
            cameraDevice?.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)?.also {
                it.addTarget(surface)
                cameraDevice?.createCaptureSession(Arrays.asList(surface),
                        object : CameraCaptureSession.StateCallback() {
                            override fun onConfigured(session: CameraCaptureSession) {
                                // カメラがcloseされている場合
                                if (null == cameraDevice) return

                                session.setRepeatingRequest(it.build(), null, null)
                            }

                            override fun onConfigureFailed(session: CameraCaptureSession) {

                            }
                        }, null)
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
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