package com.example.sixtcars.data.remote


open class Resource<T> private constructor(
    val status: Status,
    var data: T?,
    val message: String?,
    val throwable: Throwable? = null
) {

    val isSuccess: Boolean
        get() = status === Status.SUCCESS && data != null

    val isError: Boolean
        get() = status === Status.ERROR

    val isLoading: Boolean
        get() = status === Status.LOADING

    companion object {

        fun <T> success(data: T, message: String? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?, throwable: Throwable?): Resource<T> {
            return Resource(Status.ERROR, data, msg, throwable)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}