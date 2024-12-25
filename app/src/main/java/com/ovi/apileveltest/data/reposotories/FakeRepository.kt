package com.ovi.apileveltest.data.reposotories

import com.ovi.apileveltest.data.ApiResult
import com.ovi.apileveltest.data.dto.FakeResponse
import kotlinx.coroutines.flow.Flow

interface FakeRepository {
    suspend fun getFakeData(): Flow<ApiResult<FakeResponse>>
}