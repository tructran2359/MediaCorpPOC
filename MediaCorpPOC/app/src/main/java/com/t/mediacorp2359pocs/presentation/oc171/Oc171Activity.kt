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
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Oc171Activity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc171Activity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oc171)

        setUpViews()
    }

    private fun setUpViews() {
        btnJson.setOnClickListener {
            loadJson(isJsonApi = true)
        }

        btnRest.setOnClickListener {
            loadJson(isJsonApi = false)
        }

        btnProto.setOnClickListener { toast("Not Available") }
    }

    private fun loadJson(isJsonApi: Boolean) {

        val endPoint = """https://s3.amazonaws.com/"""
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
                val contentLength = response.body()?.contentLength()
                val message =
                      "Sent at:      $sentTime" +
                    "\nReceive at:   $recvTime" +
                    "\nResponseTime: $diff" +
                    "\nContent Length: $contentLength" +
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
        if (isJsonApi) {
            jsonService.loadJson().enqueue(callback)
        } else {
            jsonService.loadRest().enqueue(callback)
        }
    }
}