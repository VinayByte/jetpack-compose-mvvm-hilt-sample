package com.vinaybyte.sample.ui.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vinaybyte.sample.data.datasource.repository.Repository
import com.vinaybyte.sample.data.model.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading
    val movies: Flow<List<MovieItem>> =
        repository.loadHomeData(
            onStart = { _isLoading.value = true },
            onCompletion = { _isLoading.value = false },
            onError = { Log.d("Error loadHomeData : ", it) }
        )
}