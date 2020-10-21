package com.xtremsystems.patientscheduler.presentation.ui.main.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.helpers.SessionManager
import com.xtremsystems.patientscheduler.presentation.ui.main.settings.change_password.ChangePasswordActivity
import com.xtremsystems.patientscheduler.presentation.ui.main.stats.SummaryStatsActivity


class SettingsFragment : Fragment() {
    private lateinit var logoutLayout: RelativeLayout
    private lateinit var changePasswordLayout: RelativeLayout
    private lateinit var statsLayout: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Settings"

        logoutLayout = view.findViewById(R.id.logoutLayout)
        changePasswordLayout = view.findViewById(R.id.changePasswordLayout)
        statsLayout = view.findViewById(R.id.statsLayout)

        logoutLayout.setOnClickListener { logoutUser() }
        changePasswordLayout.setOnClickListener { changePassword() }
        statsLayout.setOnClickListener { startActivity(Intent(context, SummaryStatsActivity::class.java)) }

        return view
    }

    private fun changePassword() {
        startActivity(Intent(context, ChangePasswordActivity::class.java))
    }

    private fun logoutUser() {
        context?.let { it ->
            AlertDialog.Builder(it).setMessage("Are you sure you want to logout?")
                .setNegativeButton("No") { dialogInterface, _ -> dialogInterface.cancel() }

                .setPositiveButton("Yes") { _, _ ->
                    context?.let { SessionManager(it).logoutUser() }

                }.show()
        }
    }

}
