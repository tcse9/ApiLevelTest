package com.ovi.apileveltest.data.reposotories

import com.ovi.apileveltest.data.ApiResult
import com.ovi.apileveltest.data.api.FakeApiService
import com.ovi.apileveltest.data.dto.FakeResponse
import com.ovi.apileveltest.data.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeRepositoryImpl @Inject constructor(private val api: FakeApiService) : FakeRepository {
    override suspend fun getFakeData(): Flow<ApiResult<FakeResponse>> {
        return safeApiCall {
            api.getFakeApi()
        }
    }
}