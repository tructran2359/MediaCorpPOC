package com.t.mediacorp2359pocs.presentation.oc170

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.t.mediacorp2359pocs.R
import kotlinx.android.synthetic.main.fragment_web_view.*

class WebViewFragment : Fragment() {

    companion object {
        const val DATA = "DATA"
        fun newInstance(data: String): WebViewFragment {
            return WebViewFragment().apply {
                val bundle = Bundle()
                bundle.putString(DATA, data)
                arguments = bundle
            }
        }
    }

    private lateinit var mData: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mData = arguments?.getString(DATA) ?: ""

        setUpViews()

        refresh()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpViews() {
        pbLoading.max = 100
        wvContent.let { wv ->
            wv.settings.let { settings ->
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
            }
            wv.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    val isLoading = newProgress != 0 && newProgress != 100
                    pbLoading.progress = newProgress
                    pbLoading.isVisible = isLoading
                }
            }
        }
    }

    private fun refresh() {
        if (mData.startsWith("https://")) {
            wvContent.loadUrl(mData)
        } else {
            wvContent.loadDataWithBaseURL("", mData, "text/html", "UTF-8", "")
        }
    }

}