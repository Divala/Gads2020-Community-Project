package com.xtremsystems.patientscheduler.presentation.ui.main.overdue.first_visit

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.data.patients.Data
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys
import com.xtremsystems.patientscheduler.presentation.PatientScheduler
import com.xtremsystems.patientscheduler.presentation.adapters.recyclerview.OverdueFirstVisitAdapter
import kotlinx.android.synthetic.main.fragment_overdue_visit.*

class OverdueFirstVisitFragment : Fragment(), OverdueFirstVisitView {
    private lateinit var presenter: OverdueFirstVisitPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_overdue_visit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = OverdueFirstVisitPresenter(this.context!!, this, PatientScheduler.service!!)

        swipeRefresh.setOnRefreshListener { getPatients() }
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)

        getPatients()
    }

    override fun getAccessToken(): String {
        return PatientScheduler.prefs?.retrievePreference(AppPrefKeys.ACCESS_TOKEN, "").toString()
    }

    private fun getPatients() {
        swipeRefresh.isRefreshing = true

        presenter.getPatients()
    }

    override fun showError(message: String) {
        swipeRefresh.isRefreshing = false

        AlertDialog.Builder(context).setMessage(message).setTitle("Error")
            .setNegativeButton("Ok") { dialogInterface, _ ->
                dialogInterface.cancel()
            }.show()
    }

    override fun showPatients(data: List<Data>?) {
        swipeRefresh.isRefreshing = false

        if (data == null) {
            tvNoPatients.visibility = View.VISIBLE
        } else {
            rvPatients.layoutManager =
                LinearLayoutManager(context)
            rvPatients.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            rvPatients.adapter = OverdueFirstVisitAdapter(data, this)
        }
    }

    override fun checkPresent(id: Int?) {
        presenter.setPatientPresent(id)
    }

    override fun refreshPatients() {
        getPatients()
    }

}
