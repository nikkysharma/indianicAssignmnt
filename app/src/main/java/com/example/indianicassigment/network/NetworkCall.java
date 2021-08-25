package com.example.indianicassigment.network;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.indianicassigment.utils.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class NetworkCall {

    //    private final Context context;
    private ProgressDialog progressDialog;
    private int requestTag;
    private ServiceCallBack serviceCallBack;
     @Inject
     NetworkCall(){

    }

    private final Callback<BaseResponse<Object>> callback = new Callback<BaseResponse<Object>>() {

        public void onResponse(@NonNull Call<BaseResponse<Object>> call, retrofit2.Response<BaseResponse<Object>> response) {


            Util.showLog(response.toString());//response.errorBody().source().readByteString()
            if(response.isSuccessful()) {
                serviceCallBack.onSuccess(requestTag, response);
//                Gson g = new Gson();
//                int code = 0;
//                String message="";
//                boolean status = true;
//                code = response.code();
//                message = response.message();
//                if (code == 500 ) {
//
//                    serviceCallBack.onFail(500, requestTag,message);
//                    return;
//                }
//                if (code == 404) {
//
//                    serviceCallBack.onFail(404, requestTag,message);
//                    return;
//                } else if (response.body() != null &&  code==401) {
////                Force Logout
//                    serviceCallBack.onFail(401, requestTag,message);
//                    return;
//                }
//
//                serviceCallBack.onSuccess(requestTag, response);
//            }else {
//                if (response.code() == 500 ) {
//                    serviceCallBack.onFail(500, requestTag,response.message());
//                    return;
//                }
//                serviceCallBack.onFail(1, requestTag,response.message());
            }
        }


        @Override
        public void onFailure(@NonNull Call call, Throwable t) {
            Util.showLog(t.getMessage());
            Util.hideProgress();

            serviceCallBack.onFail(0,requestTag,t.getMessage());
        }
    };

    /**
     * Gets service call back.
     *
     * @return the service call back
     */
    public ServiceCallBack getServiceCallBack() {
        return serviceCallBack;
    }

    /**
     * Sets service call back.
     *
     * @param serviceCallBack the service call back
     */
    public void setServiceCallBack(ServiceCallBack serviceCallBack) {
        this.serviceCallBack = serviceCallBack;
    }

    /**
     * Gets request tag.
     *
     * @return the request tag
     */
    public int getRequestTag() {
        return requestTag;
    }

    /**
     * Request callback callback.
     *
     * @return the callback
     */
    public Callback requestCallback() {

        return callback;
    }

    /**
     * Sets request tag.
     *
     * @param requestType the request type
     */
    public void setRequestTag(int requestType) {
        this.requestTag = requestType;
    }




    /**
     * Gets retrofit.
     *
     * @param isShowLoading the is show loading
     * @param pass          the pass
     * @return the retrofit
     */



    public IApi getRetrofit(boolean isShowLoading, final boolean pass, final Context context) {

        if (Util.isOnline(context)) {

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES).writeTimeout(5,TimeUnit.MINUTES);

            httpClient.addInterceptor(new Interceptor() {
                @NonNull
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder builder = original.newBuilder();
                    builder.addHeader("Content-Type", "application/json");
                    builder.addHeader("Accept", "application/json");
                    builder.addHeader("Version-Code", "1.0");
                    builder.addHeader("Device-Type", "android");


                        builder.header("Authorization", "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMDI3MDAyOGJlMmYxNmZlYTdiY2ZhOTE0MDAwZTkwOTM4NDhjOTk3MmUyZDI2ZmYzMTRkZDc5NGZkMDE0ZWM1M2U5NjA2ODAzNWYwM2Q2NTIiLCJpYXQiOiIxNjIyNzE3Mjk2Ljc1Njg1MCIsIm5iZiI6IjE2MjI3MTcyOTYuNzU2ODU1IiwiZXhwIjoiMTY1NDI1MzI5Ni42NzAwMTYiLCJzdWIiOiIxNzQiLCJzY29wZXMiOltdfQ.OswEhO67ADXpmSjHYuIQi-THZm_s4M1eHqrsPl9S2U0Zy7gsluPzdYeSe5H63Oz1zoamdDN36D598To0WmdZNvObEWfdlm-Ke58aZas2zHrBTo4FGUoTQBnInBm4bIgm7Unxj_MSkz0QXfjzdRBP-YXOwqUFu9gOlOOAv49xVtgA6d-Dcxlvkka78e6jpJ3QPuTP-ObOTmJphDQ_BWFQGf7qwFUMbB6oDRbi8Id1ufWK9QV7jD72lx8F7jZUWd81-1ZhMbGuUm1Ij97cJSvUKjWAwlp7Odbas33VpYRzLXI0tVZw8pMaqWBHNAPlC_Ymo_m-UsKCsBiLE0UoMmZlmj-vo-iAmaAGB38e4nIhe6qzkhkLvQI8vzfC4sQ--1VWKTfZK_5lFA3Uw7tIsj0f_PtVBPrAcSAUsw0aqAmfqp3qIU3gWA228JdRDPlgaNueTmf2dQr9Sht8IjutDFyPSnRY_sSN8x-gWbaSDscW3OSp_JWbSJcNiMzhwYdTJE4yxJX3MvV2j8d4WgRCVneLlbHK8sybUI4-XQVVSs4TgHt6Zo0tiCOAouJ8urpivXDzOQmkKBfQNHhoiaTrn3aMeCKaRJPIbSFxQp2xlhboJkkvObJRqyJ9fAlR86T809vlOqxNOoFUpnMmWlNkzmOBEAvSffuagdUCjghcpU4Ejvc");
//



                    Request request = builder.method(original.method(), original.body())
                            .header("Accept-Encoding", "identity")
                            .build();

                    return chain.proceed(request);
                }
            });
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);


            httpClient.addInterceptor(logging);  // <-- this is the important line!


            Retrofit retrofit = new Retrofit.Builder()

                    .baseUrl(IApi.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();


            if (isShowLoading) {
                Util.showProgress(context);
            }
            // prepare call in Retrofit 2.0

            return retrofit.create(IApi.class);
        } else {

            serviceCallBack.onFail(2,requestTag,"Api is Not Call");
            return null;
        }
    }


}
