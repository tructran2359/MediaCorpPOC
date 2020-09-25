package com.t.mediacorp2359pocs.presentation.oc171

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.aperto.mediacorp.protobuf.protos.IndexProto
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.di.NetworkModule
import com.t.mediacorp2359pocs.model.json.LargeJsonResponse
import com.t.mediacorp2359pocs.model.json.flatten
import com.t.mediacorp2359pocs.utils.mapper.toUi
import com.t.mediacorp2359pocs.utils.toast
import kotlinx.android.synthetic.main.activity_oc171_large_data.btnJson
import kotlinx.android.synthetic.main.activity_oc171_large_data.btnProto
import kotlinx.android.synthetic.main.activity_oc171_large_data.pbLoading
import kotlinx.android.synthetic.main.activity_oc171_large_data.rvContent
import kotlinx.android.synthetic.main.activity_oc171_large_data.tvLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

open class Oc171LargeDataActivity : AppCompatActivity() {

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
    protected val mApiServiceJson = NetworkModule.createApiService()
    private val mApiServiceProto = NetworkModule.createApiServiceProto()

    protected val mJsonCallback = object : Callback<LargeJsonResponse> {
        override fun onResponse(
            call: Call<LargeJsonResponse>,
            response: Response<LargeJsonResponse>
        ) {
            val data = response.body()
            response.logTime()

            showData(data)
        }

        override fun onFailure(call: Call<LargeJsonResponse>, t: Throwable) {
            hideLoading()
            showError(t)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oc171_large_data)

        setUpViews()

    }

    protected open fun setUpViews() {

        btnJson.setOnClickListener {
            callApi(type = TYPE_JSON)
        }

        btnProto.setOnClickListener {
            callApi(type = TYPE_PROTO)
        }

        rvContent.let { rv ->
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = mAdapter
        }
    }

    protected fun callApi(type: Int) {
        resetLog()
        resetViews()

        mStartApi = System.currentTimeMillis()
        showLoading()

        callApiByType(type)
    }

    protected open fun callApiByType(type: Int) {
        when (type) {
            TYPE_JSON -> {
                loadLargeJsonApi()
            }

            TYPE_PROTO -> {
                loadProtoApi()
            }

            else -> {
                hideLoading()
            }
        }
    }

    private fun loadLargeJsonApi() {
        mApiServiceJson.loadLargeJson().enqueue(mJsonCallback)
    }

    private fun showData(data: Map<String, Any>?) {
        val list = data?.flatten() ?: emptyList()
        val startRendering = System.currentTimeMillis()
        rvContent.doOnPreDraw {
            mRenderedTime = System.currentTimeMillis() - startRendering
            tvLog.text = getLogMessage()
            hideLoading()
        }

        mAdapter.items = list
    }

    private fun loadProtoApi() {
        val callback = object : Callback<IndexProto.Index> {
            override fun onResponse(
                call: Call<IndexProto.Index>,
                response: Response<IndexProto.Index>
            ) {
                val data = response.body()
                response.logTime()
                val dataMap = data?.toUi()
                showData(dataMap)
            }

            override fun onFailure(call: Call<IndexProto.Index>, t: Throwable) {
                hideLoading()
                showError(t)
            }
        }

        mApiServiceProto.loadLargeProtobuff().enqueue(callback)
    }

    private fun showLoading() {
        pbLoading.isVisible = true
    }

    protected fun hideLoading() {
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
        tvLog.text = ""
        mAdapter.items = emptyList()
    }

    private fun Response<*>.logTime() {
        val sentTime = raw().sentRequestAtMillis()
        val recvTime = raw().receivedResponseAtMillis()
        mResponseTime = recvTime - sentTime
        mReceivedTime = System.currentTimeMillis() - mStartApi
    }

    private fun getLogMessage(): String {
        return "Response time: $mResponseTime ms" +
            "\nData is received: $mReceivedTime ms" +
            "\nView count: ${mAdapter.itemCount}" +
            "\nScreen is rendered: $mRenderedTime ms" +
            "\nTotal time: ${mRenderedTime + mReceivedTime} ms"
    }
}