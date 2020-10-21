package com.xtremsystems.patientscheduler.data.patients.stats

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StatsData {
    @SerializedName("ndilande")
    @Expose
    var ndilande: Int? = null
    @SerializedName("limbe")
    @Expose
    var limbe: Int? = null
    @SerializedName("total_enrolled")
    @Expose
    var total_enrolled: Int? = null
    @SerializedName("day8Hosp")
    @Expose
    var day8Hosp: Int? = null
    @SerializedName("day29Hosp")
    @Expose
    var day29Hosp: Int? = null
    @SerializedName("totalHosp")
    @Expose
    var totalHosp: Int? = null
    @SerializedName("day8Sputum")
    @Expose
    var day8Sputum: Int? = null
    @SerializedName("day29Sputum")
    @Expose
    var day29Sputum: Int? = null
    @SerializedName("totalSputum")
    @Expose
    var totalSputum: Int? = null
    @SerializedName("day8Xray")
    @Expose
    var day8Xray: Int? = null
    @SerializedName("day29Xray")
    @Expose
    var day29Xray: Int? = null
    @SerializedName("totalXray")
    @Expose
    var totalXray: Int? = null
    @SerializedName("day8Death")
    @Expose
    var day8Death: Int? = null
    @SerializedName("day29Death")
    @Expose
    var day29Death: Int? = null
    @SerializedName("totalDeath")
    @Expose
    var totalDeath: Int? = null
    @SerializedName("day8Loss")
    @Expose
    var day8Loss: Int? = null
    @SerializedName("day29Loss")
    @Expose
    var day29Loss: Int? = null
    @SerializedName("totalLoss")
    @Expose
    var totalLoss: Int? = null

}