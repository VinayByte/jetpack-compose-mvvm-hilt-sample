package com.vinaybyte.sample.data.datasource.repository

import androidx.annotation.WorkerThread
import com.vinaybyte.sample.data.Resource
import com.vinaybyte.sample.data.datasource.remote.ApiService
import com.vinaybyte.sample.data.model.MovieItem
import com.vinaybyte.sample.data.model.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import retrofit2.Response
import javax.inject.Inject

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
class Repository @Inject constructor(
    private val service: ApiService
) {
    @WorkerThread
    fun loadHomeData(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ): Flow<List<MovieItem>> {
        return object : NetworkRepository<MoviesResponse>() {
            override suspend fun fetchFromNetwork(): Response<MoviesResponse> = service.getMovies()
        }.asFlow()
            .onStart { onStart() }
            .onCompletion { onCompletion() }
            .flowOn(Dispatchers.IO)
            .map { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data.results
                    }

                    is Resource.Error -> {
                        onError(result.message)
                        emptyList()
                    }
                }
            }
    }
}