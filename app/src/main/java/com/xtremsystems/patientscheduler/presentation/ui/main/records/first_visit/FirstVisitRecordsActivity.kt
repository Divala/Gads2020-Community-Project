package com.xtremsystems.patientscheduler.presentation.ui.main.records.first_visit

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys
import com.xtremsystems.patientscheduler.presentation.PatientScheduler
import com.xtremsystems.patientscheduler.presentation.ui.main.MainActivity
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_first_visit_records.*

class FirstVisitRecordsActivity : AppCompatActivity(),
    FirstVisitRecordsView {
    private lateinit var presenter: FirstVisitRecordsPresenter
    lateinit var dialog: android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_visit_records)

        setSupportActionBar(toolbar as Toolbar?)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.title = intent.getStringExtra("PATIENT_NAME")

        (toolbar as Toolbar).navigationIcon?.setColorFilter(
            ContextCompat.getColor(this, R.color.colorPrimary),
            PorterDuff.Mode.SRC_ATOP
        )
        (toolbar as Toolbar?)?.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary))

        btnSave.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirm Action")
                .setMessage("Are you sure about the results?")
                .setPositiveButton("Yes") { _, _ -> postData(intent.getStringExtra("PATIENT_NAME")) }
                .setNegativeButton("No") { dialogInterface, _ -> dialogInterface.cancel() }
                .show()
        }

        presenter = FirstVisitRecordsPresenter(
            this,
            this,
            PatientScheduler.service!!
        )

    }

    private fun postData(patientName: String?) {
        dialog = SpotsDialog.Builder().setContext(this).setMessage("Saving results...").build().apply { show() }

        patientName?.let { presenter.postData(it) }
    }

    override fun getAccessToken(): String {
        return PatientScheduler.prefs?.retrievePreference(AppPrefKeys.ACCESS_TOKEN, "").toString()
    }

    override fun getXray(): String {
        if (cbTbByXray.isChecked) {
            return cbTbByXray.text.toString()
        }
        return ""
    }

    override fun getSputum(): String {
        if (cbTbBySputum.isChecked) {
            return cbTbBySputum.text.toString()
        }
        return ""
    }

    override fun getDeath(): String {
        if (cbDeath.isChecked) {
            return cbDeath.text.toString()
        }
        return ""
    }

    override fun getHospitalization(): String {
        if (cbHospitalization.isChecked) {
            return cbHospitalization.text.toString()
        }
        return ""
    }

    override fun getLossToFollowUp(): String {
        if (cbLossToFollowUp.isChecked) {
            return cbLossToFollowUp.text.toString()
        }
        return ""
    }

    override fun getNonOfTheAbove(): String {
        if (cbNon.isChecked) {
            return cbNon.text.toString()
        }
        return ""
    }

    override fun showMessage(s: String) {
        dialog.dismiss()

        AlertDialog.Builder(this).setTitle("Success").setMessage(s).setNegativeButton("Ok") { dialogInterface, _ ->
            startActivity(Intent(this, MainActivity::class.java))
            dialogInterface.cancel()
        }.show()
    }

    override fun showError(s: String) {
        dialog.dismiss()

        AlertDialog.Builder(this).setTitle("Error").setMessage(s).setNegativeButton("Ok") { dialogInterface, _ ->
            dialogInterface.cancel()
        }.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
