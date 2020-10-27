package com.xtremsystems.patientscheduler.data.remote

import com.xtremsystems.patientscheduler.data.auth.AuthResponse
import com.xtremsystems.patientscheduler.data.patients.PatientResponse
import com.xtremsystems.patientscheduler.data.patients.add.AddPatientResponse
import com.xtremsystems.patientscheduler.data.patients.results.ResultsResponse
import com.xtremsystems.patientscheduler.data.patients.stats.PatientsStatsResponse
import com.xtremsystems.patientscheduler.helpers.AppConfig
import retrofit2.Call
import retrofit2.http.*

interface PatientSchedulerService {
    @FormUrlEncoded
    @Headers("key:" + AppConfig.APP_SECRET)
    @POST("auth/login")
    fun attemptLogin(@Field("email") name: String, @Field("password") password: String): Call<AuthResponse>

    @GET("patients/first-visit")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun getFirstVisitPatients(@Header("Authorization") token: String): Call<PatientResponse>

    @GET("patients/second-visit")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun getSecondVisitPatients(@Header("Authorization") token: String): Call<PatientResponse>

    @FormUrlEncoded
    @POST("patients/update-first-visit")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun setPresent(@Header("Authorization") token: String, @Field("id") id: Int?): Call<PatientResponse>

    @FormUrlEncoded
    @POST("patients/update-missed-first-visit")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun setAbsent(@Header("Authorization") token: String, @Field("id") id: Int?): Call<PatientResponse>

    @FormUrlEncoded
    @POST("patients/update-second-visit")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun setSecondVisitPresent(@Header("Authorization") token: String, @Field("id") id: Int?): Call<PatientResponse>

    @FormUrlEncoded
    @POST("patients/update-missed-second-visit")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun setSecondVisitAbsent(@Header("Authorization") token: String, @Field("id") id: Int?): Call<PatientResponse>

    @GET("patients/missed-first-visit")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun getOverdueFirstVisit(@Header("Authorization") token: String): Call<PatientResponse>

    @GET("patients/missed-second-visit")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun getOverdueSecondVisitPatients(@Header("Authorization") token: String): Call<PatientResponse>

    @FormUrlEncoded
    @POST("patients/add")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun addPatient(@Header("Authorization") token: String, @Field("patient_name") patientId: String, @Field("location") location: String): Call<AddPatientResponse>


    @GET("patients/all")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun getAllPatients(@Header("Authorization") token: String): Call<PatientResponse>

    @FormUrlEncoded
    @POST("auth/users/change-password")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun changePassword(@Field("old") oldPassword: String, @Field("new") newPassword: String, @Header("Authorization") token: String): Call<AuthResponse>

    @FormUrlEncoded
    @POST("results/first-visit")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun postFirstVisitRecords(
        @Header("Authorization") token: String,
        @Field("id") patient_id: Int,
        @Field("tb_by_x-ray") xray: String,
        @Field("tb_by_sputum") sputum: String,
        @Field("death") death: String,
        @Field("hospitalisation") hosp: String,
        @Field("loss_to_follow_up") loss: String,
        @Field("non") non: String
    ): Call<ResultsResponse>

    @FormUrlEncoded
    @POST("results/second-visit")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun postSecondVisitRecords(
        @Header("Authorization") token: String,
        @Field("id") patient_id: Int,
        @Field("tb_by_x-ray") xray: String,
        @Field("tb_by_sputum") sputum: String,
        @Field("death") death: String,
        @Field("hospitalisation") hosp: String,
        @Field("loss_to_follow_up") loss: String,
        @Field("non") non: String
    ): Call<ResultsResponse>

    @GET("stats/today-total")
    @Headers("key:" + AppConfig.APP_SECRET)
    fun getSummaryStats(@Header("Authorization") token: String): Call<PatientsStatsResponse>
}