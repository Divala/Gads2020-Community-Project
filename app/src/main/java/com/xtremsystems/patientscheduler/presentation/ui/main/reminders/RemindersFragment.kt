package com.xtremsystems.patientscheduler.presentation.ui.main.reminders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.presentation.adapters.fragments.RemindersPagerAdapter
import kotlinx.android.synthetic.main.fragment_reminders.*

class RemindersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_reminders, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Today's Patients"

        remindersTabs.tabTextColors =
            context?.let { ContextCompat.getColorStateList(it, R.color.colorPrimary) }

        remindersTabs.setupWithViewPager(viewPager)
        viewPager.adapter = RemindersPagerAdapter(childFragmentManager)
    }

}
