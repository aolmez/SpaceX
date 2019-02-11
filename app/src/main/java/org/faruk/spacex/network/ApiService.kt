package org.faruk.spacex.network

import org.faruk.spacex.common.BASE_URL
import org.faruk.spacex.common.LAUNCHES_SUFFIX
import org.faruk.spacex.model.Launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET(LAUNCHES_SUFFIX)
    fun getLaunches(): Call<List<Launch>?>

    companion object {
        val instance: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            retrofit.create(ApiService::class.java)
        }

    }
}