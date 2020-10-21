package com.xtremsystems.patientscheduler.presentation.ui.main.stats

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_summary_stats.*
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.data.patients.stats.StatsData
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys
import com.xtremsystems.patientscheduler.presentation.PatientScheduler

class SummaryStatsActivity : AppCompatActivity(), SummaryStatsView {
    private lateinit var presenter: SummaryStatsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary_stats)

        setSupportActionBar(toolbar as Toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        (toolbar as Toolbar).navigationIcon?.setColorFilter(
            ContextCompat.getColor(this, R.color.colorPrimary),
            PorterDuff.Mode.SRC_ATOP
        )
        (toolbar as Toolbar?)?.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        presenter = SummaryStatsPresenter(this, this, PatientScheduler.service!!)

        progressBar.visibility = View.VISIBLE

        getStats()
    }

    private fun getStats() {
        presenter.getStats()
    }

    override fun getAccessToken(): String {
        return PatientScheduler.prefs?.retrievePreference(AppPrefKeys.ACCESS_TOKEN, "").toString()
    }

    override fun showError(s: String) {
        progressBar.visibility = View.INVISIBLE

        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(s)
            .setNegativeButton("Ok") { dialogInterface, _ -> dialogInterface.cancel() }
            .show()
    }

    override fun showStats(data: StatsData?) {
        progressBar.visibility = View.INVISIBLE

        tvNdixStats.text = data?.ndilande.toString()
        tvLimbeStats.text = data?.limbe.toString()
        tvEnrollTotalStats.text = data?.total_enrolled.toString()

        tvXrayStats8.text = data?.day8Xray.toString()
        tvXrayStats29.text = data?.day29Xray.toString()
        tvTotalXrayStats.text = data?.totalXray.toString()

        tvTodayHopsStats8.text = data?.day8Hosp.toString()
        tvTodayHopsStats29.text = data?.day29Hosp.toString()
        tvHospTotalStats.text = data?.totalHosp.toString()

        tvSputumStatsDay8.text = data?.day8Sputum.toString()
        tvSputumStats29.text = data?.day29Sputum.toString()
        tvTodaySputumStats.text = data?.totalSputum.toString()

        tvTodayDeathStats8.text = data?.day8Death.toString()
        tvTodayDeathStat29.text = data?.day29Death.toString()
        tvDeathTotalStats.text = data?.totalDeath.toString()


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
