package com.xtremsystems.patientscheduler.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.play.core.appupdate.AppUpdateManagerFactory.create
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.UpdateAvailability
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.data.patients.Patient
import com.xtremsystems.patientscheduler.helpers.SessionManager
import com.xtremsystems.patientscheduler.presentation.PatientScheduler
import com.xtremsystems.patientscheduler.presentation.ui.main.add_patient.AddPatientFragment
import com.xtremsystems.patientscheduler.presentation.ui.main.all_patients.AllPatientsFragment
import com.xtremsystems.patientscheduler.presentation.ui.main.overdue.OverdueFragment
import com.xtremsystems.patientscheduler.presentation.ui.main.reminders.RemindersFragment
import com.xtremsystems.patientscheduler.presentation.ui.main.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {
    private lateinit var presenter: MainPresenter
    private val MY_REQUEST_CODE = 1001

    override fun onStart() {
        super.onStart()
        SessionManager(this).checkLogin()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar as Toolbar)

        (toolbar as Toolbar?)?.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary))

        presenter = MainPresenter(this, this, PatientScheduler.service!!)

        bottomNav.setOnNavigationItemSelectedListener { item ->
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.action_reminder -> {
                    transaction.replace(R.id.frame_layout, RemindersFragment()).commit()
                }
                R.id.action_overdue -> {
                    transaction.replace(R.id.frame_layout, OverdueFragment()).commit()
                }
                R.id.action_add -> {
                    transaction.replace(R.id.frame_layout, AddPatientFragment()).commit()
                }
                R.id.action_all -> {
                    transaction.replace(R.id.frame_layout, AllPatientsFragment()).commit()
                }
                R.id.action_settings -> {
                    transaction.replace(R.id.frame_layout, SettingsFragment()).commit()
                }
            }
            true
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigationView.selectedItemId = R.id.action_reminder

    }

    override fun shoPendingPatients(patients: List<Patient>?) {
    }

    override fun showError(s: String) {
        AlertDialog.Builder(this).setTitle("Error")
            .setMessage(s)
            .setNegativeButton("Ok") { dialogInterface, _ -> dialogInterface.cancel() }
            .show()
    }

    private fun checkUpdate() {

        val appUpdateManager = create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(IMMEDIATE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo, IMMEDIATE, this, MY_REQUEST_CODE
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val appUpdateManager = create(this)

        appUpdateManager
            .appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        IMMEDIATE,
                        this,
                        MY_REQUEST_CODE
                    )
                }
            }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                checkUpdate()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }

}
