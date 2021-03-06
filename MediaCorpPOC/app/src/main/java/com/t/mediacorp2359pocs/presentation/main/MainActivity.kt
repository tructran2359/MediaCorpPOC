package com.t.mediacorp2359pocs.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.presentation.oc1473_algolia.Oc1473Activity
import com.t.mediacorp2359pocs.presentation.oc170.Oc170Activity
import com.t.mediacorp2359pocs.presentation.oc171.Oc171Activity
import com.t.mediacorp2359pocs.presentation.oc171.Oc171LargeDataActivity
import com.t.mediacorp2359pocs.presentation.oc171_json_vs_rest.Oc171JsonVsRestActivity
import com.t.mediacorp2359pocs.presentation.oc2097_liveblog.Oc2079LiveBlogActivity
import com.t.mediacorp2359pocs.presentation.oc379.Oc379Activity
import com.t.mediacorp2359pocs.presentation.oc813.Oc813Activity
import kotlinx.android.synthetic.main.activity_main.btnOc1473
import kotlinx.android.synthetic.main.activity_main.btnOc170
import kotlinx.android.synthetic.main.activity_main.btnOc171
import kotlinx.android.synthetic.main.activity_main.btnOc171JsonVsRest
import kotlinx.android.synthetic.main.activity_main.btnOc171LargeData
import kotlinx.android.synthetic.main.activity_main.btnOc2097
import kotlinx.android.synthetic.main.activity_main.btnOc379
import kotlinx.android.synthetic.main.activity_main.btnOc813

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews()
    }

    private fun setUpViews() {
        btnOc170.setOnClickListener {
            startActivity(Oc170Activity.getLaunchIntent(this))
        }

        btnOc171.setOnClickListener {
            startActivity(Oc171Activity.getLaunchIntent(this))
        }

        btnOc171LargeData.setOnClickListener {
            startActivity(Oc171LargeDataActivity.getLaunchIntent(this))
        }

        btnOc379.setOnClickListener {
            startActivity(Oc379Activity.getLaunchIntent(this))
        }

        btnOc171JsonVsRest.setOnClickListener {
            startActivity(Oc171JsonVsRestActivity.getLaunchIntent(this))
        }

        btnOc813.setOnClickListener {
            startActivity(Oc813Activity.getLaunchIntent(this))
        }

        btnOc1473.setOnClickListener {
            startActivity(Oc1473Activity.getLaunchIntent(this))
        }

        btnOc2097.setOnClickListener {
            startActivity(Oc2079LiveBlogActivity.getLaunchIntent(this))
        }
    }
}