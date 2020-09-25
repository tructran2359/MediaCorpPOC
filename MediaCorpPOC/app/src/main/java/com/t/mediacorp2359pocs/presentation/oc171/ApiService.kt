package com.t.mediacorp2359pocs.presentation.oc171

import com.aperto.mediacorp.protobuf.protos.ArticleProto
import com.aperto.mediacorp.protobuf.protos.IndexProto
import com.t.mediacorp2359pocs.model.json.JsonResponse
import com.t.mediacorp2359pocs.model.json.LargeJsonResponse
import com.t.mediacorp2359pocs.model.oc379.request.WidgetRequest
import com.t.mediacorp2359pocs.model.oc379.response.WidgetsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET
    fun loadJson(@Url url: String = "https://www.channelnewsasia.com/blueprint/servlet/protobuf/article/13073470?xxx"): Call<JsonResponse>

    @GET
    fun loadRest(@Url url: String = "https://demo3704578.mockable.io/rest"): Call<ResponseBody>

    @GET
    fun loadProtobuff(@Url url: String = "https://www.channelnewsasia.com/blueprint/servlet/protobuf/article/13073470"): Call<ArticleProto.Article>

    @GET
    fun loadLargeJson(@Url url: String = "https://www.channelnewsasia.com/blueprint/servlet/protobuf/index?extend=true&xxx"): Call<LargeJsonResponse>

    @GET
    fun loadLargeProtobuff(@Url url: String = "https://www.channelnewsasia.com/blueprint/servlet/protobuf/index?extend=true"): Call<IndexProto.Index>

    @GET
    fun loadJson0(@Url url: String = "https://demo3704578.mockable.io/json"): Call<LargeJsonResponse>

    @GET
    fun loadRest0(@Url url: String = "https://demo3704578.mockable.io/rest"): Call<LargeJsonResponse>

    @POST
    fun loadWidgets(
        @Url url: String = "https://recommend-zoom.mediacorp.sg/api/v1/loadWidget",
        @Query("token") token: String = "e6f6h28e26vbc8442b288eb6121d85b9a4",
        @Body request: WidgetRequest
    ): Call<WidgetsResponse>
}