package com.xtremsystems.patientscheduler.presentation.adapters.recyclerview

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.patient_item.view.*
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.data.patients.Data
import com.xtremsystems.patientscheduler.presentation.ui.main.records.first_visit.FirstVisitRecordsActivity

class FirstVisitAdapter(private var patients: MutableList<Data>) :
    RecyclerView.Adapter<PatientsVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PatientsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_item, parent, false)

        return PatientsVH(view, patients)
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
    }

    fun removeAt(position: Int) {
        patients.removeAt(position)

        notifyItemRemoved(position)
    }
}

class PatientsVH(itemView: View, patients: List<Data>) :
    RecyclerView.ViewHolder(itemView) {

    val patientId: TextView = itemView.tvPatientId

    init {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, FirstVisitRecordsActivity::class.java)
            intent.putExtra("PATIENT_ID", patients[adapterPosition].id)
            intent.putExtra("PATIENT_NAME", patients[adapterPosition].patientId)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME)
            itemView.context.startActivity(intent)
        }
    }


}