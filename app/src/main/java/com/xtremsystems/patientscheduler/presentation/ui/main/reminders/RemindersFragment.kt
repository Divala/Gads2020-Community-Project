package com.xtremsystems.patientscheduler.presentation.ui.main.reminders

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.presentation.adapters.fragments.RemindersPagerAdapter

class RemindersFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var remindersTabs: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_reminders, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Today's Patients"

        viewPager = view.findViewById(R.id.viewPager)
        remindersTabs = view.findViewById(R.id.remindersTabs)

        remindersTabs.tabTextColors = context?.let { ContextCompat.getColorStateList(it, R.color.colorPrimary) }

        remindersTabs.setupWithViewPager(viewPager)
        viewPager.adapter = RemindersPagerAdapter(childFragmentManager)

        return view
    }

}
