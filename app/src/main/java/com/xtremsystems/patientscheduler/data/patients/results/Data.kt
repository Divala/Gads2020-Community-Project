package com.xtremsystems.patientscheduler.data.patients.results

data class Data(
    val created_at: String,
    val death: Any,
    val hospitalisation: Any,
    val id: Int,
    val loss_to_follow_up: Any,
    val non: String,
    val patient_id: String,
    val recorded_by: Int,
    val tb_by_sputum: Any,
    val tb_by_x_ray: Any,
    val updated_at: String
)