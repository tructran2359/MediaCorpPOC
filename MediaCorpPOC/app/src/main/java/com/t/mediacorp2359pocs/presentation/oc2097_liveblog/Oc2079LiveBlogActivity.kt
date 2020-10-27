package com.t.mediacorp2359pocs.presentation.oc2097_liveblog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.t.mediacorp2359pocs.databinding.ActivityOc2097LiveblogBinding

class Oc2079LiveBlogActivity : AppCompatActivity() {

    companion object {
        const val EVENT_ID = "2636807179789895648"
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc2079LiveBlogActivity::class.java)
        }
    }

    private lateinit var mBinding: ActivityOc2097LiveblogBinding
    private lateinit var mPagerAdapter: LiveBlogPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityOc2097LiveblogBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpViews()
    }

    private fun setUpViews() {

        mPagerAdapter = LiveBlogPagerAdapter(this)

        mBinding.viewPager.adapter = mPagerAdapter
        mBinding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager) { tab, position ->
            tab.text = mPagerAdapter.getPageTitle(position)
        }.attach()
    }

}