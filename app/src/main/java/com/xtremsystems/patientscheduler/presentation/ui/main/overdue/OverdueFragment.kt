package com.xtremsystems.patientscheduler.presentation.ui.main.overdue

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
import com.xtremsystems.patientscheduler.presentation.adapters.fragments.OverduePagerAdapter

class OverdueFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var overdueTabs: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_overdue, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Overdue Patients"

        viewPager = view.findViewById(R.id.viewPager)
        overdueTabs = view.findViewById(R.id.overdueTabs)

        overdueTabs.tabTextColors = context?.let { ContextCompat.getColorStateList(it, R.color.colorPrimary) }

        overdueTabs.setupWithViewPager(viewPager)
        viewPager.adapter = OverduePagerAdapter(childFragmentManager)

        return view
    }

}
