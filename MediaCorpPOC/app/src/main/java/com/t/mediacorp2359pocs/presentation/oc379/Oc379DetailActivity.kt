package com.t.mediacorp2359pocs.presentation.oc379

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.databinding.ActivityOc379DetailBinding
import com.t.mediacorp2359pocs.di.NetworkModule
import com.t.mediacorp2359pocs.model.oc379.ui.Widget
import com.t.mediacorp2359pocs.utils.fromServerToLocalDateTime
import com.t.mediacorp2359pocs.utils.load
import com.t.mediacorp2359pocs.utils.toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class Oc379DetailActivity : AppCompatActivity() {

    companion object {
        const val WIDGET = "WIDGET"
        fun getLaunchIntent(context: Context, widget: Widget): Intent {
            return Intent(context, Oc379DetailActivity::class.java).apply {
                putExtra(WIDGET, widget)
            }
        }
    }

    private lateinit var mBinding: ActivityOc379DetailBinding
    private val mApiService = NetworkModule.createApiService()
    private var mWidget: Widget? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mWidget = intent.getParcelableExtra(WIDGET)

        mBinding = ActivityOc379DetailBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        mWidget?.let { widget ->
            showWidgetDetail(widget)
            submitClickTracker(widget.clickTracker)
        }
    }

    private fun showWidgetDetail(widget: Widget) {
        mBinding.run {
            ivImage.load(widget.thumbnail)
            tvTitle.text = widget.title
            tvDate.text = widget.publishDate.fromServerToLocalDateTime()
            tvSection.text = widget.section
            tvDesc.setText(R.string.text_placeholder)
        }
    }

    private fun submitClickTracker(url: String) {
        val callback = object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                toast("Click Tracker: DONE")
                hideLoading()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                toast("Click Tracker: ERROR")
                hideLoading()
                Timber.e(t)
            }
        }

        showLoading()
        mApiService.trackWidgetClicked(url).enqueue(callback)
    }

    private fun showLoading() {
        mBinding.pbLoading.isVisible = true
    }

    private fun hideLoading() {
        mBinding.pbLoading.isGone = true
    }

}