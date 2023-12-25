package com.vinaybyte.sample.data.datasource.remote

import com.vinaybyte.sample.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
interface ApiService {
    @GET("/JsonSandbox/apis/movies.json")
    suspend fun getMovies(): Response<MoviesResponse>
}