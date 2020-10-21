package com.xtremsystems.patientscheduler.presentation

import android.app.Application
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService
import com.xtremsystems.patientscheduler.helpers.AppConfig.Companion.BASE_URL
import com.xtremsystems.patientscheduler.helpers.SharedPreferenceManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PatientScheduler : Application() {
    companion object {
        var service: PatientSchedulerService? = null
        var prefs: SharedPreferenceManager? = null
    }

    override fun onCreate() {
        super.onCreate()

        prefs = SharedPreferenceManager(applicationContext)

        service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PatientSchedulerService::class.java)

    }
}