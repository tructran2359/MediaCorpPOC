package com.t.mediacorp2359pocs.presentation.oc170

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PageAdapter(
    fm: FragmentManager,
    private val iframes: List<String>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return iframes.size
    }

    override fun getItem(position: Int): Fragment {
        return WebViewFragment.newInstance(iframes[position])
    }
}