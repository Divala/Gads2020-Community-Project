package com.xtremsystems.patientscheduler.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.data.patients.Data
import com.xtremsystems.patientscheduler.helpers.DatesFormat
import kotlinx.android.synthetic.main.patient_all_item.view.*
import kotlinx.android.synthetic.main.patient_item.view.tvPatientId

class PatientsAdapter(private var patients: List<Data>) :
    RecyclerView.Adapter<PatientsVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PatientsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_all_item, parent, false)

        return PatientsVH(view)
    }

    override fun getItemCount(): Int {
        if (patients.isEmpty()) {
            return 0
        }
        return patients.size

    }

    override fun onBindViewHolder(holder: PatientsVH, position: Int) {
        val patient: Data = patients[position]
        holder.patientId.text = patient.patientId.toString()
        holder.tvDate.text = "Added on " + patient.createdAt?.let { DatesFormat.getFullDate(it) }
    }
}

class PatientsVH(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val patientId: TextView = itemView.tvPatientId
    val tvDate: TextView = itemView.tvDate

}
