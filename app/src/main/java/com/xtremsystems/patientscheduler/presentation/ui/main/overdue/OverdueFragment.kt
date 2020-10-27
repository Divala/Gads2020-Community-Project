package com.xtremsystems.patientscheduler.presentation.ui.main.overdue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.xtremsystems.patientscheduler.R
import com.xtremsystems.patientscheduler.presentation.adapters.fragments.OverduePagerAdapter
import kotlinx.android.synthetic.main.fragment_overdue.*

class OverdueFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_overdue, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Overdue Patients"


        overdueTabs.tabTextColors =
            context?.let { ContextCompat.getColorStateList(it, R.color.colorPrimary) }

        overdueTabs.setupWithViewPager(viewPager)
        viewPager.adapter = OverduePagerAdapter(childFragmentManager)
    }

}
