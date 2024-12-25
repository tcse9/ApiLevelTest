package com.ovi.apileveltest.data.api

import com.ovi.apileveltest.data.dto.FakeResponse
import retrofit2.Response
import retrofit2.http.GET

interface FakeApiService {
    @GET("fake_base_endpoint")
    suspend fun getFakeApi(): Response<FakeResponse>
}