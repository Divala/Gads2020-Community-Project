package com.xtremsystems.patientscheduler.presentation.ui.main.settings.change_password

import android.app.AlertDialog
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys.Companion.ACCESS_TOKEN
import com.xtremsystems.patientscheduler.helpers.SessionManager
import com.xtremsystems.patientscheduler.presentation.PatientScheduler
import com.xtremsystems.patientscheduler.presentation.ui.login.LoginActivity
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_change_password.*


class ChangePasswordActivity : AppCompatActivity(), ChangePasswordView {
    private lateinit var presenter: ChangePasswordPresenter
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        setSupportActionBar(toolbar as Toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        (toolbar as Toolbar).navigationIcon?.setColorFilter(
            ContextCompat.getColor(this, R.color.colorPrimary),
            PorterDuff.Mode.SRC_ATOP
        )
        (toolbar as Toolbar?)?.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary))

        presenter = ChangePasswordPresenter(this, this, PatientScheduler.service!!)

        changePasswordBtn.setOnClickListener {
            dialog = SpotsDialog.Builder().setContext(this).setMessage("Changing password...").build()
            dialog.show()

            presenter.changePassword()
        }
    }

    override fun getOldPassword(): String {
        return editTextOldPassword.text.toString()
    }

    override fun showOldPasswordError(s: String) {
        dialog.dismiss()
        oldPasswordTI.error = s
    }

    override fun getNewPassword(): String {
        return editTextNewPassword.text.toString()
    }

    override fun showNewPasswordError(s: String) {
        dialog.dismiss()
        newPasswordTI.error = s
    }

    override fun getConfirmPassword(): String {
        return editTextConfirmPassword.text.toString()
    }

    override fun showConfirmPasswordError(s: String) {
        dialog.dismiss()
        confirmPasswordTI.error = s
    }

    override fun getAccessToken(): String {
        return PatientScheduler.prefs?.retrievePreference(ACCESS_TOKEN, "").toString()
    }

    override fun goToLoginActivity() {
        dialog.dismiss()
        SessionManager(this).logoutUser()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun showError(s: String) {
        dialog.dismiss()
        Snackbar.make(changePasswordLayout, s, Snackbar.LENGTH_LONG).show()
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
