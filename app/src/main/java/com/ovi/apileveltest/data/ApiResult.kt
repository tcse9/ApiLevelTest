package com.ovi.apileveltest.data

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.io.IOException

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val code: Int?, val message: String?, val throwable: Throwable? = null) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<ApiResult<T>> = flow {
    emit(ApiResult.Loading)
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ApiResult.Success(it))
            } ?: emit(ApiResult.Error(response.code(), "Response body is null"))
        } else {
            emit(ApiResult.Error(response.code(), response.message()))
        }
    } catch (e: IOException) {
        emit(ApiResult.Error(null, e.message, e))
    } catch (e: Exception) {
        emit(ApiResult.Error(null, e.message, e))
    }

}.flowOn(Dispatchers.IO)