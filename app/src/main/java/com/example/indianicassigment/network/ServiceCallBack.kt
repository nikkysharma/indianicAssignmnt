package com.example.indianicassigment.network

import retrofit2.Response

interface ServiceCallBack {
    /**
     * On success.
     * @param tag          the tag
     * @param baseResponse the base response
     */
    fun onSuccess(tag: Int, baseResponse: Response<BaseResponse<Any?>>)

    /**
     * On fail.
     *
     * @param t   the t
     * @param tag the tag
     */
    fun onFail(code: Int, tag: Int, message: String)

}