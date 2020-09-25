package com.t.mediacorp2359pocs.presentation.oc379

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.di.NetworkModule
import com.t.mediacorp2359pocs.mapper.toUi
import com.t.mediacorp2359pocs.model.oc379.request.WidgetContext
import com.t.mediacorp2359pocs.model.oc379.request.WidgetRequest
import com.t.mediacorp2359pocs.model.oc379.response.WidgetsResponse
import com.t.mediacorp2359pocs.presentation.oc171.ApiService
import com.t.mediacorp2359pocs.presentation.oc171.ResponseAdapter
import com.t.mediacorp2359pocs.utils.loadHtml
import com.t.mediacorp2359pocs.utils.toast
import kotlinx.android.synthetic.main.activity_oc379.btnRestApi
import kotlinx.android.synthetic.main.activity_oc379.btnWebView
import kotlinx.android.synthetic.main.activity_oc379.pbLoading
import kotlinx.android.synthetic.main.activity_oc379.rvWidgets
import kotlinx.android.synthetic.main.activity_oc379.webView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class Oc379Activity : AppCompatActivity() {

    companion object {
        const val ID = "2E5pZFZtWux2"
        const val MEID = "feea1fb7-093e-4904-842d-4efaa7e9856b"
        const val SITE = "cna"
        const val CXENSE_ID = "k4s54akt21t2sd8c"
        const val URL = "https://www.channelnewsasia.com/news/business/singapore-world-past-peak-trade-globalisation-cptpp-rcep-asean-12929886"
        const val CONTENT_ID = "12929886"
        const val DEVICE_ID = "38400000-8cf0-11bd-b23e-10b96e40000d"
        const val LANG = "en"

        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc379Activity::class.java)
        }

        const val HTML_CODE = """
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta name="viewport"content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<script async src="https://recommend-zoom.mediacorp.sg/tag.js?network=mediacorp"></script>
		<script>
			window.recApp=window.recApp||{};
			recApp.context={
				isWebView:true,
				site: '{{site}}',
				lang: '{{lang}}',
				url: '{{url}}',
				content_id: '{{content_id}}',
				meid: '{{meid}}',
				device_id: '{{device_id}}',
				cxense_id: '{{cxense_id}}',
			};
		</script>
	</head>
	
	<body>
		<div id="{{widget_id}}" data-role="rec-abtesting"></div>
	</body>

</html>
        """
    }

    private lateinit var mApiService: ApiService
    private val mAdapter = WidgetAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_oc379)

        setUpViews()

        mApiService = NetworkModule.createApiService()
    }

    private fun setUpViews() {
        setUpRecyclerView()
        setUpWebView()
        btnRestApi.setOnClickListener {
            loadApi()
        }

        btnWebView.setOnClickListener {
            loadToWebView()
        }
    }

    private fun setUpRecyclerView() {
        mAdapter.itemClick = { widget ->
            toast("Click: $widget")
        }
        rvWidgets.let { rv ->
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = mAdapter
            val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            val drawable = ContextCompat.getDrawable(this, R.drawable.item_decoration)
            drawable?.let {
                dividerItemDecoration.setDrawable(it)
            }
            rv.addItemDecoration(dividerItemDecoration)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() {
        webView.let { wv ->
            wv.settings.let { settings ->
                settings.javaScriptEnabled = true
            }
        }
    }

    private fun loadApi() {
        webView.isGone = true
        rvWidgets.isVisible = true
        val callback = object : Callback<WidgetsResponse> {
            override fun onResponse(call: Call<WidgetsResponse>, response: Response<WidgetsResponse>) {
                val data = response.body()
                hideLoading()

                data?.let { widgetsResponse ->
                    mAdapter.submitList(widgetsResponse.data.items.toUi())
                }
            }

            override fun onFailure(call: Call<WidgetsResponse>, t: Throwable) {
                hideLoading()
                Timber.e(t)
                toast("Error")
            }
        }

        showLoading()

        val widgetContext = WidgetContext(
            meid = MEID,
            site = SITE,
            cxense_id = CXENSE_ID,
            url = URL,
            content_id = CONTENT_ID
        )
        val request = WidgetRequest(
            id = ID,
            context = widgetContext
        )

        mApiService.loadWidgets(request = request).enqueue(callback)
    }

    private fun loadToWebView() {
        webView.isVisible = true
        rvWidgets.isGone = true
        val fullHtmlCode = HTML_CODE
            .replace("{{site}}", SITE)
            .replace("{{lang}}", LANG)
            .replace("{{url}}", URL)
            .replace("{{content_id}}", CONTENT_ID)
            .replace("{{meid}}", MEID)
            .replace("{{device_id}}", DEVICE_ID)
            .replace("{{cxense_id}}", CXENSE_ID)
            .replace("{{widget_id}}", ID)

        Timber.d(fullHtmlCode)

        webView.loadHtml(fullHtmlCode)
    }

    private fun showLoading() {
        pbLoading.isVisible = true
    }

    private fun hideLoading() {
        pbLoading.isGone = true
    }
}