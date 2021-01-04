package com.shanu.avengersstats.data.repository

import Hero
import com.shanu.avengersstats.api.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getDetails(id:String):Response<Hero>{
        return RetrofitInstance.api.getDetails(id)

    }
}