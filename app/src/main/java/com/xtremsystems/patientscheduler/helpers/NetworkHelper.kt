package com.xtremsystems.patientscheduler.helpers

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import java.util.concurrent.TimeUnit

object NetworkHelper {
    fun hasInternetConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun getRequestErrorMessage(error: ResponseBody): String {
        return try {
            val json = JsonParser().parse(error.string()).asJsonObject
            json.getAsJsonObject("result").get("message").asString
        } catch (e: Exception) {
            e.printStackTrace()
            "Error while processing request. Please try again later"
        }

    }

    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }


}
