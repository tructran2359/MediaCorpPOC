package com.t.mediacorp2359pocs.presentation.oc171

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("prod.tracker2/resource/106123163/json-api-response.json?response-content-disposition=inline&response-content-type=application%2Fjson&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAJJBSFJ4TCVKKGAIA%2F20200831%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20200831T071958Z&X-Amz-Expires=1800&X-Amz-SignedHeaders=host&X-Amz-Signature=2a5aa875c53b159d9b3c9755863a023288bd4f6e552c5fc66dae226fadea5223")
    fun loadJson(): Call<ResponseBody>

    @GET("prod.tracker2/resource/106123167/rest-api-response.json?response-content-disposition=inline&response-content-type=application%2Fjson&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAJJBSFJ4TCVKKGAIA%2F20200831%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20200831T072120Z&X-Amz-Expires=1800&X-Amz-SignedHeaders=host&X-Amz-Signature=ee92efc882831f56d7babadd88c943b1f5da95bb76ecba9cf66bbe6eb8848699")
    fun loadRest(): Call<ResponseBody>
}