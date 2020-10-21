package com.xtremsystems.patientscheduler.presentation.ui.main.add_patient

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys
import com.xtremsystems.patientscheduler.presentation.PatientScheduler
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_add_patient.*


class AddPatientFragment : Fragment(), AddPatientView {
    private lateinit var presenter: AddPatientPresenter

    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_add_patient, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Patient"

        presenter = AddPatientPresenter(this.context!!, this, PatientScheduler.service!!)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnAdd.setOnClickListener {
            dialog = SpotsDialog.Builder().setContext(context).setMessage("Logging in...").build()
                .apply { show() }

            addPatient()
        }

    }

    private fun addPatient() {
        presenter.addPatient()
    }

    override fun getAccessToken(): String {
        return PatientScheduler.prefs?.retrievePreference(AppPrefKeys.ACCESS_TOKEN, "").toString()
    }

    override fun getPatientName(): String {
        return etPatientName.text.toString()
    }

    override fun getLocation(): String {
        return when (rgLocation.checkedRadioButtonId) {
            R.id.rbLimbe -> rbLimbe.text.toString()
            R.id.rbNdilande -> rbNdilande.text.toString()

            else -> ""
        }
    }

    override fun showLocationError(s: String) {
        dialog.dismiss()

        AlertDialog.Builder(context)
            .setMessage(s)
            .setNegativeButton("Ok") { dialogInterface, _ -> dialogInterface.cancel() }
            .show()
    }

    override fun showPatientIdError(s: String) {
        dialog.dismiss()

        tiPatientName.error = s
    }

    override fun showError(message: String) {
        dialog.dismiss()

        AlertDialog.Builder(context).setMessage(message).setTitle("Error")
            .setNegativeButton("Ok") { dialogInterface, _ ->
                dialogInterface.cancel()
            }.show()
    }


    override fun showMessage(message: String) {
        dialog.dismiss()

        AlertDialog.Builder(context).setMessage(message).setTitle("Success")
            .setNegativeButton("Ok") { dialogInterface, _ ->
                dialogInterface.cancel()
                etPatientName.setText("")
            }.show()
    }
}
