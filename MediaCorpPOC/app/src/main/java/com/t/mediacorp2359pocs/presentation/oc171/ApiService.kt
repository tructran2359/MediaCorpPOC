package com.t.mediacorp2359pocs.presentation.oc171

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    fun loadJson(@Url url: String = "https://www.channelnewsasia.com/blueprint/servlet/protobuf/article/13073470?xxx"): Call<ResponseBody>

    @GET
    fun loadRest(@Url url: String = "https://s3.amazonaws.com/prod.tracker2/resource/106123167/rest-api-response.json?response-content-disposition=inline&response-content-type=application%2Fjson&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAJJBSFJ4TCVKKGAIA%2F20200903%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20200903T061044Z&X-Amz-Expires=1800&X-Amz-SignedHeaders=host&X-Amz-Signature=ecf69f000d8ba61f4cbb0aba5b1799580d3390c2c673028f29f4a804316156f4"): Call<ResponseBody>

    @GET
    fun loadProtobuff(@Url url: String = "https://www.channelnewsasia.com/blueprint/servlet/protobuf/article/13073470"): Call<ResponseBody>
}