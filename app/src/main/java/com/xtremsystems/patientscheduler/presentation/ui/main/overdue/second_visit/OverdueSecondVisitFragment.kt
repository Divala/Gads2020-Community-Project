package com.xtremsystems.patientscheduler.presentation.ui.main.overdue.second_visit

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
import com.xtremsystems.patientscheduler.presentation.adapters.recyclerview.OverdueSecondVisitAdapter
import kotlinx.android.synthetic.main.fragment_overdue_visit.*

class OverdueSecondVisitFragment : Fragment(), OverdueSecondVisitView {
    private lateinit var presenter: OverdueSecondVisitPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_overdue_visit, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = OverdueSecondVisitPresenter(this.context!!, this, PatientScheduler.service!!)

        swipeRefresh.setOnRefreshListener { getPatients() }
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)

        getPatients()
    }

    override fun getAccessToken(): String {
        return PatientScheduler.prefs?.retrievePreference(AppPrefKeys.ACCESS_TOKEN, "").toString()
    }

    private fun getPatients() {
        swipeRefresh.isRefreshing = true

        presenter.getOverduePatients()
    }

    override fun showError(message: String) {
        swipeRefresh.isRefreshing = false

        if (context == null) return
        AlertDialog.Builder(context).setMessage(message).setTitle("Error")
            .setNegativeButton("Ok") { dialogInterface, _ ->
                dialogInterface.cancel()
            }.show()
    }

    override fun showPatients(data: List<Data>?) {
        swipeRefresh.isRefreshing = false
        if (context == null) return

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
            rvPatients.adapter = OverdueSecondVisitAdapter(data, this)
        }
    }

    override fun checkPresent(id: Int?) {
        presenter.setPresentCheck(id)
    }

    override fun refreshPatients() {
        getPatients()
    }
}
