package com.example.cryptocurrency.core.domain.util

sealed interface Result<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : Result<D, E>
    data class Error<out T, out E : RootError>(val error: E) : Result<T, E>
}

inline fun <T, E : RootError, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Success -> Result.Success(map(data))
        is Result.Error -> Result.Error(error)
    }
}

fun <T, E : RootError> Result<T, E>.asEmptyDataResult(): Result<Unit, E> {
    return map { }
}

inline fun <T, E : RootError> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    if (this is Result.Success) {
        action(data)
    }
    return this
}

inline fun <T, E : RootError> Result<T, E>.onFailure(action: (E) -> Unit): Result<T, E> {
    if (this is Result.Error) {
        action(error)
    }
    return this
}