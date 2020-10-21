package com.xtremsystems.patientscheduler.presentation.ui.main.reminders.first_visit

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.data.patients.Data
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys
import com.xtremsystems.patientscheduler.presentation.PatientScheduler
import com.xtremsystems.patientscheduler.presentation.adapters.recyclerview.FirstVisitAdapter
import com.xtremsystems.patientscheduler.presentation.adapters.recyclerview.SwipeToDeleteCallback

class FirstVisitReminderFragment : Fragment(), FirstVisitRemindersView,
    SwipeToDeleteCallback.RecyclerItemTouchHelperListener {
    private lateinit var presenter: FirstVisitRemindersPresenter
    private lateinit var tvNoPatients: TextView
    private lateinit var rvPatients: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var patients: List<Data>
    private lateinit var adapter: FirstVisitAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_visit_reminders, container, false)

        presenter = FirstVisitRemindersPresenter(this.context!!, this, PatientScheduler.service!!)

        tvNoPatients = view.findViewById(R.id.tvNoPatients)
        rvPatients = view.findViewById(R.id.rvPatients)
        swipeRefresh = view.findViewById(R.id.swipeRefresh)

        swipeRefresh.setOnRefreshListener { getPatients() }
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)

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
            adapter = FirstVisitAdapter(data as MutableList<Data>)
            rvPatients.adapter = adapter


            patients = data

            val itemTouchHelper = context?.let { SwipeToDeleteCallback(it, this) }?.let { ItemTouchHelper(it) }
            itemTouchHelper?.attachToRecyclerView(rvPatients)
        }
    }

    override fun showError(message: String?) {
        swipeRefresh.isRefreshing = false

        AlertDialog.Builder(context).setMessage(message).setTitle("Error").setNegativeButton(
            "Ok"
        ) { dialogInterface, _ -> dialogInterface.cancel() }.show()
    }

    override fun checkPresent(id: Int?) {
        presenter.setPresent(id)
    }

    override fun checkAbsent(id: Int?) {
        presenter.setAbsent(id)
    }

    override fun refreshPatients() {
        presenter.getPatients()
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        AlertDialog.Builder(context).setTitle("Confirm Action")
            .setMessage("Are you sure that " + patients[viewHolder.adapterPosition].patientId + " is absent?")
            .setPositiveButton("Yes") { _, _ ->
                presenter.setAbsent(patients[viewHolder.adapterPosition].id)
                adapter.removeAt(viewHolder.adapterPosition)
            }
            .setNegativeButton("No") { _, _ -> getPatients() }
            .show()
    }
}
