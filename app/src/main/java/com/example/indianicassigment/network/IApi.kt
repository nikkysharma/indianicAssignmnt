package com.example.indianicassigment.network

import com.example.indianicassigment.data.model.api.MaxtraResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IApi {
    @POST("get_total_request")
    fun callListService(@Body body: RequestBody?): Call<BaseResponse<List<MaxtraResponse>>>

    companion object {
        const val BASE_URL = "http://182.76.237.238/~teammaxtra/help_application/public/api/"

        // -----------------------Pending Request  END-------------------------
        const val LIST_TAG = 1001
    }
}