package com.t.mediacorp2359pocs.presentation.oc171

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.aperto.mediacorp.protobuf.protos.ArticleProto
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.di.NetworkModule
import com.t.mediacorp2359pocs.mapper.toUiModel
import com.t.mediacorp2359pocs.model.json.JsonResponse
import com.t.mediacorp2359pocs.model.ui.UiModel
import com.t.mediacorp2359pocs.utils.joinToStringWithLineBreak
import com.t.mediacorp2359pocs.utils.openExternalBrowser
import com.t.mediacorp2359pocs.utils.textAsString
import com.t.mediacorp2359pocs.utils.toast
import com.t.mediacorp2359pocs.utils.underline
import kotlinx.android.synthetic.main.activity_oc171.*
import kotlinx.android.synthetic.main.item_fragment.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.system.measureTimeMillis

class Oc171Activity : AppCompatActivity() {

    companion object {

        const val BASE_URL = "https://www.channelnewsasia.com"

        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc171Activity::class.java)
        }

        const val TYPE_JSON = 1
        const val TYPE_REST = 2
        const val TYPE_PROTO = 3
    }

    private var mResponseTime = 0L
    private var mReceivedTime = 0L
    private var mRenderedTime = 0L
    private var mStartApi = 0L

    private var mHeaderImagesAdapter = HeaderImagesAdapter()
    private var mFragmentsAdapter = FragmentsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oc171)

        setUpViews()

        resetViews()
    }

    private fun setUpViews() {

        tvWebUrl.underline()
        tvWebUrl.setOnClickListener {
            onWebUrlClick(tvWebUrl.textAsString())
        }

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
        resetViews()

        mStartApi = System.currentTimeMillis()
        showLoading()

        when (type) {
            TYPE_JSON -> {
                loadJsonApi()
            }

            TYPE_PROTO -> {
                loadProtoApi()
            }

            TYPE_REST -> {
                loadRestApi()
            }

            else -> {
            }
        }
    }

    private fun loadJsonApi() {
        val mApiService = NetworkModule.createApiService()

        val callback = object : Callback<JsonResponse> {
            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
                val data = response.body()
                response.logTime()
                hideLoading()
                showData(data?.toUiModel(), true)
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                hideLoading()
                showError(t)
            }
        }

        mApiService.loadJson().enqueue(callback)
    }

    private fun loadProtoApi() {
        val mApiService = NetworkModule.createApiServiceProto()
        val callback = object : Callback<ArticleProto.Article> {
            override fun onResponse(
                call: Call<ArticleProto.Article>,
                response: Response<ArticleProto.Article>
            ) {
                response.logTime()
                val data = response.body()
                hideLoading()
                showData(data?.toUiModel(), true)
            }

            override fun onFailure(call: Call<ArticleProto.Article>, t: Throwable) {
                hideLoading()
                showError(t)
            }
        }

        mApiService.loadProtobuff().enqueue(callback)
    }

    private fun loadRestApi() {
        val apiService = NetworkModule.createApiService()

        val callback = object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.logTime()
                val string = response.body()?.string() ?: ""
                hideLoading()
                showRest(string)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                hideLoading()
                showError(t)
            }
        }

        apiService.loadRest().enqueue(callback)
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
        tvMessage.text = ""
        showData(UiModel.dummy())
    }

    private fun Response<*>.logTime() {
        val sentTime = raw().sentRequestAtMillis()
        val recvTime = raw().receivedResponseAtMillis()
        mResponseTime = recvTime - sentTime
        mReceivedTime = System.currentTimeMillis()
    }

    @SuppressLint("SetTextI18n")
    private fun showRest(string: String) {
        tvRest.isVisible = true
        llContent.isGone = true
        tvRest.doOnPreDraw {
            mRenderedTime = System.currentTimeMillis()
            val logMessage = getLogMessage()
            tvRest.text = logMessage +
                "\nContent:" +
                "\n$string"
        }
    }

    private fun showData(model: UiModel?, needLogTime: Boolean = false) {
        tvRest.isGone = true
        llContent.isVisible = true

        val nonNullModel = model ?: UiModel.dummy()
        if (needLogTime) {
            rvFragments.doOnPreDraw {
                mRenderedTime = System.currentTimeMillis()
                val message = getLogMessage()
                tvMessage.text = message
            }
        }

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

    private fun getLogMessage(): String {
        return "Response time: $mResponseTime ms" +
            "\nDataResponse is received: ${mReceivedTime - mStartApi} ms" +
            "\nScreen is rendered: ${mRenderedTime - mReceivedTime} ms" +
            "\nTotal time: ${mRenderedTime - mStartApi} ms"
    }

    private fun onWebUrlClick(url: String) {
        when {
            url.isEmpty() -> {
                toast("Empty Url")
            }
            url.startsWith('/') -> {
                val fullUrl = BASE_URL + url
                openExternalBrowser(fullUrl)
            }
            else -> {
                openExternalBrowser(url)
            }
        }
    }
}