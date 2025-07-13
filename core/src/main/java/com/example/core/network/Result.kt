package com.example.core.network

interface RootError

sealed interface Result<D, E: RootError> {
    data class Success<D, E: RootError>(val data: D): Result<D, E>
    data class Error<D, E: RootError>(val error: E): Result<D, E>
}