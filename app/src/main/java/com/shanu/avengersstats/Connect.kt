package com.shanu.avengersstats

import com.shanu.avengersstats.Url.Companion.BASE_URL
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Connect {

    companion object {

        private fun getRetrofit(Url:String): Retrofit {
            return Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(Url)
                .build()
        }

        fun getApiData():Retrofit{
            val retrofitApi = getRetrofit(Url.BASE_URL)
            return retrofitApi
        }

        fun callApi():CallApi{
            val retrofitCall = getApiData()
            return retrofitCall.create(CallApi::class.java)
        }

    }
}
interface CallApi {

    @GET(Url.URL)
//query needed if there is any query
    fun getApi(@Query("limit") limit: Int):
//model class is needed
            Observable<Model.Result>
}
