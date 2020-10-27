package com.xtremsystems.patientscheduler.presentation.ui.main.reminders.second_visit

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.data.patients.Data
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys
import com.xtremsystems.patientscheduler.presentation.PatientScheduler
import com.xtremsystems.patientscheduler.presentation.adapters.recyclerview.SecondVisitAdapter
import com.xtremsystems.patientscheduler.presentation.adapters.recyclerview.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.fragment_visit_reminders.*


class SecondVisitReminderFragment : Fragment(), SecondVisitReminderView,
    SwipeToDeleteCallback.RecyclerItemTouchHelperListener {
    private lateinit var presenter: SecondVisitReminderPresenter
    private lateinit var patients: List<Data>
    private lateinit var adapter: SecondVisitAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_visit_reminders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefresh.setOnRefreshListener { getPatients() }
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)

        presenter = SecondVisitReminderPresenter(this.context!!, this, PatientScheduler.service!!)

        getPatients()

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
            rvPatients.adapter = SecondVisitAdapter(data as MutableList<Data>)

            patients = data

            val itemTouchHelper =
                context?.let { SwipeToDeleteCallback(it, this) }?.let { ItemTouchHelper(it) }
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
        AlertDialog.Builder(context).setTitle(patients[viewHolder.adapterPosition].patientId)
            .setMessage("Are you sure that the patient is absent?")
            .setPositiveButton("Yes") { _, _ ->
                presenter.setAbsent(patients[viewHolder.adapterPosition].id)
                adapter.removeAt(viewHolder.adapterPosition)
            }
            .setNegativeButton("No") { dialogInterface, _ -> dialogInterface.cancel() }
            .show()
    }
}
