package com.xtremsystems.patientscheduler.presentation.ui.main.add_patient

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.KeyEvent
import com.journeyapps.barcodescanner.CaptureManager
import com.xtremsystems.patientscheduler.R
import kotlinx.android.synthetic.main.activity_custom_scanner.*
import pub.devrel.easypermissions.EasyPermissions

class BarcodeScannerActivity : AppCompatActivity() {
    private var capture: CaptureManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_scanner)

        zxing_barcode_scanner.setStatusText("")

        EasyPermissions.requestPermissions(this, getString(R.string.camera_permission), RESULT_CAPTURE_ID, OPEN_CAMERA)

        capture = CaptureManager(this, zxing_barcode_scanner)
        capture!!.initializeFromIntent(intent, savedInstanceState)
        capture!!.decode()
    }

    override fun onResume() {
        super.onResume()
        capture!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture!!.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture!!.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return zxing_barcode_scanner!!.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }

    companion object {
        private val OPEN_CAMERA = Manifest.permission.CAMERA
        private val RESULT_CAPTURE_ID = 1001
    }
}
