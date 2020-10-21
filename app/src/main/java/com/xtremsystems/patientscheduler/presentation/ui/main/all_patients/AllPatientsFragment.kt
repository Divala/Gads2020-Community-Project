package com.xtremsystems.patientscheduler.presentation.ui.main.all_patients

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_all_patients.*
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.data.patients.Data
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys
import com.xtremsystems.patientscheduler.presentation.PatientScheduler
import com.xtremsystems.patientscheduler.presentation.adapters.PatientsAdapter

class AllPatientsFragment : Fragment(), AllPatientsView {
    private lateinit var presenter: AllPatientsPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rvPatients: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_all_patients, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "All Patients"

        presenter = AllPatientsPresenter(this.context!!, this, PatientScheduler.service!!)

        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener { getPatients() }
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)

        rvPatients = view.findViewById(R.id.rvPatients)

        rvPatients.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


        getPatients()

        return view
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
            val mLayoutManager =
                LinearLayoutManager(context)

            mLayoutManager.reverseLayout = true
            mLayoutManager.stackFromEnd = true
            rvPatients.layoutManager = mLayoutManager
            rvPatients.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )

            rvPatients.adapter = PatientsAdapter(data)
        }
    }
}
