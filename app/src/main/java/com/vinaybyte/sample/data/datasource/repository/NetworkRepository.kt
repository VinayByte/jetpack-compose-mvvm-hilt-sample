package com.vinaybyte.sample.data.datasource.repository

import androidx.annotation.MainThread
import com.vinaybyte.sample.data.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
abstract class NetworkRepository<RESULT> {

    fun asFlow() = flow<Resource<RESULT>> {
        val response = fetchFromNetwork()
        val responseData = response.body()
        if (response.isSuccessful && responseData != null) {
            emit(Resource.Success(responseData))
        } else {
            emit(Resource.Error(response.message()))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Error("Something went wrong!"))
    }

    /**
     * Fetch [Response] from the network.
     */
    @MainThread
    protected abstract suspend fun fetchFromNetwork(): Response<RESULT>
}