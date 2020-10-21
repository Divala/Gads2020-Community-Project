package com.xtremsystems.patientscheduler.presentation.adapters.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.xtremsystems.patientscheduler.presentation.ui.main.reminders.first_visit.FirstVisitReminderFragment
import com.xtremsystems.patientscheduler.presentation.ui.main.reminders.second_visit.SecondVisitReminderFragment

class RemindersPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> FirstVisitReminderFragment()
            1 -> SecondVisitReminderFragment()

            else -> FirstVisitReminderFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? = if (position == 0) "Day 8" else "Day 29"

}