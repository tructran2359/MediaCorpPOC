package com.t.mediacorp2359pocs.presentation.oc171

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.utils.toast
import kotlinx.android.synthetic.main.activity_oc171.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Oc171Activity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc171Activity::class.java)
        }

        const val TYPE_JSON = 1
        const val TYPE_REST = 2
        const val TYPE_PROTO = 3
    }

    private var mContentSize = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oc171)

        setUpViews()
    }

    private fun setUpViews() {
        btnJson.setOnClickListener {
            loadJson(type = TYPE_JSON)
        }

        btnRest.setOnClickListener {
            loadJson(type = TYPE_REST)
        }

        btnProto.setOnClickListener {
            loadJson(type = TYPE_PROTO)
        }
    }

    private fun loadJson(type: Int) {

        val endPoint = "https://www.channelnewsasia.com/"
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(endPoint)
            .build()

        val jsonService = retrofit.create(ApiService::class.java)
        val callback = object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val sentTime = response.raw().sentRequestAtMillis()
                val recvTime = response.raw().receivedResponseAtMillis()
                val diff = recvTime - sentTime
                val content = response.body()?.string()
                val contentLength = content?.toByteArray()?.size

                val message =
                      "Sent at:      $sentTime" +
                    "\nReceive at:   $recvTime" +
                    "\nResponseTime: $diff" +
                    "\nContent Length: $contentLength" +
                    "\n" +
                    "\nContent: $content"
                tvContent.text = message
                pbLoading.isGone = true
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val message = "Error: ${t.message}"
                tvContent.text = message
                pbLoading.isGone = true
            }
        }

        pbLoading.isVisible = true
        when(type) {
            TYPE_JSON -> jsonService.loadJson().enqueue(callback)

            TYPE_REST -> jsonService.loadRest().enqueue(callback)

            TYPE_PROTO -> jsonService.loadProtobuff().enqueue(callback)
        }
    }
}