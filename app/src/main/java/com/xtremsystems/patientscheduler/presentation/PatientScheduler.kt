package com.xtremsystems.patientscheduler.presentation

import android.app.Application
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService
import com.xtremsystems.patientscheduler.helpers.AppConfig.Companion.BASE_URL
import com.xtremsystems.patientscheduler.helpers.NetworkHelper
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.getOkHttpClient
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
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
            .create(PatientSchedulerService::class.java)

    }
}