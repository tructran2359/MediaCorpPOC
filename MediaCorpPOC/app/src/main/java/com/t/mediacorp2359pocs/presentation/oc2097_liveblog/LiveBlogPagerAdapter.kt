package com.t.mediacorp2359pocs.presentation.oc2097_liveblog

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LiveBlogPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LiveBlogDetailFragment.newInstance()

            1 -> LiveBlogKeyPointsFragment.newInstance()

            else -> throw RuntimeException("Invalid Page Position: $position")
        }
    }

    fun getPageTitle(position: Int): String = when(position) {
        0 -> "DETAIL"
        1 -> "KEY POINTS"
        else -> ""
    }
}
