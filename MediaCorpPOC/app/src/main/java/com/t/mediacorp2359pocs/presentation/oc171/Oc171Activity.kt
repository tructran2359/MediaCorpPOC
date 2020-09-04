package com.t.mediacorp2359pocs.presentation.oc171

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.di.NetworkModule
import com.t.mediacorp2359pocs.model.json.JsonResponse
import com.t.mediacorp2359pocs.model.ui.UiModel
import com.t.mediacorp2359pocs.utils.joinToStringWithLineBreak
import com.t.mediacorp2359pocs.utils.toast
import kotlinx.android.synthetic.main.activity_oc171.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class Oc171Activity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc171Activity::class.java)
        }

        const val TYPE_JSON = 1
        const val TYPE_REST = 2
        const val TYPE_PROTO = 3
    }

    private var mResponseTime = 0L
    private var mReceivedTime = 0L
    private var mHeaderImagesAdapter = HeaderImagesAdapter()
    private var mFragmentsAdapter = FragmentsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oc171)

        setUpViews()

        showData(UiModel.dummy())
    }

    private fun setUpViews() {
        rvHeaderImages.let { rv ->
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = mHeaderImagesAdapter
        }

        rvFragments.let { rv ->
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = mFragmentsAdapter
        }

        btnJson.setOnClickListener {
            callApi(type = TYPE_JSON)
        }

        btnRest.setOnClickListener {
            callApi(type = TYPE_REST)
        }

        btnProto.setOnClickListener {
            callApi(type = TYPE_PROTO)
        }
    }

    private fun callApi(type: Int) {
        resetLog()

        when (type) {
            TYPE_JSON -> {
                loadJsonApi()
            }

            else -> {
            }
        }
    }

    private fun loadJsonApi() {
        val apiService = NetworkModule.createApiService()
        val callback = object : Callback<JsonResponse> {
            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
                val data = response.body()
                response.logTime()
                hideLoading()
                showData(data?.toUiModel())
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                toast("ERROR")
                Timber.e(t)
            }
        }

        showLoading()
        apiService.loadJson().enqueue(callback)
    }

    private fun showLoading() {
        pbLoading.isVisible = true
    }

    private fun hideLoading() {
        pbLoading.isGone = true
    }

    private fun resetLog() {
        mReceivedTime = 0L
        mResponseTime = 0L
    }

    private fun Response<*>.logTime() {
        val sentTime = raw().sentRequestAtMillis()
        mReceivedTime = raw().receivedResponseAtMillis()
        mResponseTime = mReceivedTime - sentTime
    }

    private fun showData(model: UiModel?) {
        val nonNullModel = model ?: UiModel.dummy()

        nonNullModel.let { m ->
            tvId.text = m.id
            tvTitle.text = m.title
            tvFeedId.text = m.feedId
            tvWebUrl.text = m.webUrl

            mHeaderImagesAdapter.items = m.headerImages

            tvCreated.text = m.created
            tvLastModified.text = m.lastModified
            tvKeywords.text = m.keywords.joinToStringWithLineBreak()
            tvCopyright.text = m.copyright
            tvArticleType.text = m.articletype
            tvCateName.text = m.categoryname
            tvDocType.text = m.docType
            tvAdsPos.text = m.adsPosition
            tvCiaKeywords.text = m.ciaKeywords.joinToStringWithLineBreak()

            mFragmentsAdapter.items = m.fragments
        }
    }
}