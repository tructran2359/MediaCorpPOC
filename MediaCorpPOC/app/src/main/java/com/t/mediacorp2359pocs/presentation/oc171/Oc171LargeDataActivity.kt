package com.t.mediacorp2359pocs.presentation.oc171

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.di.NetworkModule
import com.t.mediacorp2359pocs.model.json.LargeJsonResponse
import com.t.mediacorp2359pocs.model.json.flatten
import com.t.mediacorp2359pocs.utils.toast
import kotlinx.android.synthetic.main.activity_oc171_large_data.btnJson
import kotlinx.android.synthetic.main.activity_oc171_large_data.btnProto
import kotlinx.android.synthetic.main.activity_oc171_large_data.btnRest
import kotlinx.android.synthetic.main.activity_oc171_large_data.pbLoading
import kotlinx.android.synthetic.main.activity_oc171_large_data.rvContent
import kotlinx.android.synthetic.main.activity_oc171_large_data.tvLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class Oc171LargeDataActivity : AppCompatActivity() {

    companion object {

        const val BASE_URL = "https://www.channelnewsasia.com"

        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc171LargeDataActivity::class.java)
        }

        const val TYPE_JSON = 1
        const val TYPE_REST = 2
        const val TYPE_PROTO = 3
    }

    private var mResponseTime = 0L
    private var mReceivedTime = 0L
    private var mRenderedTime = 0L
    private var mStartApi = 0L

    private val mAdapter = ResponseAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oc171_large_data)

        setUpViews()

    }

    private fun setUpViews() {

        btnJson.setOnClickListener {
            callApi(type = TYPE_JSON)
        }

        btnRest.setOnClickListener {
            callApi(type = TYPE_REST)
        }

        btnProto.setOnClickListener {
            callApi(type = TYPE_PROTO)
        }

        rvContent.let { rv ->
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = mAdapter
        }
    }

    private fun callApi(type: Int) {
        resetLog()
        resetViews()

        mStartApi = System.currentTimeMillis()
        showLoading()

        when (type) {
            TYPE_JSON -> {
                loadLargeJsonApi()
            }

            TYPE_PROTO -> {
                loadProtoApi()
            }

            TYPE_REST -> {
                loadRestApi()
            }

            else -> {
                hideLoading()
            }
        }
    }

    private fun loadLargeJsonApi() {
        val apiService = NetworkModule.createApiService()

        val callback = object : Callback<LargeJsonResponse> {
            override fun onResponse(
                call: Call<LargeJsonResponse>,
                response: Response<LargeJsonResponse>
            ) {
                val data = response.body()
                response.logTime()
                hideLoading()

                showData(data)
            }

            override fun onFailure(call: Call<LargeJsonResponse>, t: Throwable) {
                hideLoading()
                showError(t)
            }
        }

        apiService.loadLargeJson().enqueue(callback)
    }

    private fun showData(data: LargeJsonResponse?) {
        rvContent.doOnPreDraw {
            mRenderedTime = System.currentTimeMillis()
            tvLog.text = getLogMessage()
        }

        mAdapter.items = data?.flatten() ?: emptyList()
    }

    private fun loadProtoApi() {
    }

    private fun loadRestApi() {
    }

    private fun showLoading() {
        pbLoading.isVisible = true
    }

    private fun hideLoading() {
        pbLoading.isGone = true
    }

    private fun showError(t: Throwable) {
        toast("ERROR: ${t.message}")
        Timber.e(t)
    }

    private fun resetLog() {
        mReceivedTime = 0L
        mResponseTime = 0L
        mStartApi = 0L
        mRenderedTime = 0L
    }

    private fun resetViews() {
    }

    private fun Response<*>.logTime() {
        val sentTime = raw().sentRequestAtMillis()
        val recvTime = raw().receivedResponseAtMillis()
        mResponseTime = recvTime - sentTime
        mReceivedTime = System.currentTimeMillis()
    }

    private fun getLogMessage(): String {
        return "Response time: $mResponseTime ms" +
            "\nData is received: ${mReceivedTime - mStartApi} ms" +
            "\nScreen is rendered: ${mRenderedTime - mReceivedTime} ms" +
            "\nTotal time: ${mRenderedTime - mStartApi} ms"
    }
}