package com.ovi.apileveltest.domain

import com.ovi.apileveltest.data.ApiResult
import com.ovi.apileveltest.data.dto.FakeResponse
import com.ovi.apileveltest.data.reposotories.FakeRepository
import com.ovi.apileveltest.domain.model.FakeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FakeDataUseCase @Inject constructor(private val repository: FakeRepository) {
    suspend operator fun invoke() : Flow<ApiResult<FakeModel>> {
        return repository.getFakeData().map { result ->
            when(result) {
                is ApiResult.Error -> ApiResult.Error(result.code, result.message, result.throwable)
                ApiResult.Loading -> ApiResult.Loading
                is ApiResult.Success -> ApiResult.Success(transformToFakeModel(result.data))
            }

        }
    }

    private fun transformToFakeModel(data: FakeResponse): FakeModel {
        return FakeModel(
            description = data.data?.get(0)?.description ?: "",
            title = data.data?.get(0)?.title ?: "",
            source = data.data?.get(0)?.source ?: ""
        )
    }
}