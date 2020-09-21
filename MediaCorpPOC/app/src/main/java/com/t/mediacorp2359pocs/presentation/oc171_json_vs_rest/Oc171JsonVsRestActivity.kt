package com.t.mediacorp2359pocs.presentation.oc171_json_vs_rest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.t.mediacorp2359pocs.presentation.oc171.Oc171LargeDataActivity
import kotlinx.android.synthetic.main.activity_oc171_large_data.btnProto

class Oc171JsonVsRestActivity : Oc171LargeDataActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc171JsonVsRestActivity::class.java)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setUpViews() {
        super.setUpViews()

        btnProto.text = "REST"
        btnProto.setOnClickListener {
            callApi(TYPE_REST)
        }
    }

    override fun callApiByType(type: Int) {
        when(type) {
            TYPE_JSON -> loadJson()

            TYPE_REST -> loadRest()

            else -> hideLoading()
        }
    }

    private fun loadJson() {
        mApiServiceJson.loadJson0().enqueue(mJsonCallback)
    }

    private fun loadRest() {
        mApiServiceJson.loadRest0().enqueue(mJsonCallback)
    }

}