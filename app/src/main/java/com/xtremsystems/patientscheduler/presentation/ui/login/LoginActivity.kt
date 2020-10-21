package com.xtremsystems.patientscheduler.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.helpers.SessionManager
import com.xtremsystems.patientscheduler.presentation.PatientScheduler
import com.xtremsystems.patientscheduler.presentation.ui.main.MainActivity
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var presenter: LoginPresenter
    lateinit var dialog: android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this, this, PatientScheduler.service!!)

        loginBtn.setOnClickListener {
            dialog = SpotsDialog.Builder().setContext(this).setMessage("Logging in...").build().apply { show() }
            presenter.login()
        }
    }

    override fun getName(): String {
        return etName.text.toString()
    }

    override fun getPassword(): String {
        return etPassword.text.toString()
    }

    override fun showNameError(s: String) {
        dialog.dismiss()

        tiName.error = s
    }

    override fun showPasswordError(s: String) {
        dialog.dismiss()

        tiPassword.error = s
    }

    override fun goToMainActivity() {
        SessionManager(this).createLoginSession()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun showError(s: String) {
        dialog.dismiss()

        AlertDialog.Builder(this).setTitle("Error").setMessage(s)
            .setNegativeButton("Ok") { dialogInterface, _ -> dialogInterface.cancel() }
            .show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }
}
