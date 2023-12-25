package com.vinaybyte.sample.data

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
}