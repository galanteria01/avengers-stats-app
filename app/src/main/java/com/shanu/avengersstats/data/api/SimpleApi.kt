package com.shanu.avengersstats.api

import Hero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {
    @GET("{id}")
    suspend fun getDetails(@Path("id") id:String) : Response<Hero>
}