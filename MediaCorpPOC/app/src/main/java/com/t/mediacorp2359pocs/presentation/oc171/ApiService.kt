package com.t.mediacorp2359pocs.presentation.oc171

import com.t.mediacorp2359pocs.model.json.JsonResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    fun loadJson(@Url url: String = "https://www.channelnewsasia.com/blueprint/servlet/protobuf/article/13073470?xxx"): Call<JsonResponse>

    @GET
    fun loadRest(@Url url: String = "https://demo3704578.mockable.io/rest"): Call<ResponseBody>

    @GET
    fun loadProtobuff(@Url url: String = "https://www.channelnewsasia.com/blueprint/servlet/protobuf/article/13073470"): Call<ResponseBody>
}