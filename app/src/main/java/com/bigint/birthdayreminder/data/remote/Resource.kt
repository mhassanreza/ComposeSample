package com.bigint.birthdayreminder.data.remote

import com.bigint.birthdayreminder.BaseApplication
import com.bigint.birthdayreminder.R
import com.bigint.birthdayreminder.enums.NetworkErrorType
import com.bigint.birthdayreminder.enums.StatusCode

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(
        val message: String,
        val errorType: NetworkErrorType = NetworkErrorType.UNKNOWN
    ) : Resource<Nothing>()
}


fun <T> filterResource(response: Resource<T>): Resource<T> {
    return if (response is Resource.Error && response.message.contains(StatusCode.NotFound.code.toString())) {
        Resource.Error(
            BaseApplication.getString(R.string.err_msg_not_found),
            NetworkErrorType.SERVER_ERROR
        )
    } else if (response is Resource.Error && response.message.contains(StatusCode.Unauthorized.code.toString())) {
        Resource.Error(
            BaseApplication.getString(R.string.err_msg_unauthorized),
            NetworkErrorType.UNAUTHORIZED
        )
    } else if (response is Resource.Error && response.message.contains(StatusCode.RequestTimeout.code.toString())) {
        Resource.Error(
            BaseApplication.getString(R.string.err_msg_request_timeout),
            NetworkErrorType.TIMEOUT
        )
    } else if (response is Resource.Error && response.message.contains(StatusCode.ServiceUnavailable.code.toString())) {
        Resource.Error(
            BaseApplication.getString(R.string.err_msg_unknwon_host),
            NetworkErrorType.UNKNOWN
        )
    } else if (response is Resource.Error && response.message.contains(StatusCode.InternalServerError.code.toString())) {
        Resource.Error(
            BaseApplication.getString(R.string.err_msg_internal_server_error),
            NetworkErrorType.UNKNOWN
        )
    } else if (response is Resource.Error && response.message.contains(StatusCode.BadRequest.code.toString())) {
        Resource.Error(
            BaseApplication.getString(R.string.err_msg_bad_request),
            NetworkErrorType.UNKNOWN
        )
    } else if (response is Resource.Error && response.message.contains(StatusCode.Forbidden.code.toString())) {
        Resource.Error(
            BaseApplication.getString(R.string.err_msg_forbidden),
            NetworkErrorType.UNKNOWN
        )
    } else if (response is Resource.Error && response.message.contains(StatusCode.MethodNotAllowed.code.toString())) {
        Resource.Error(
            BaseApplication.getString(R.string.err_msg_method_not_allowed),
            NetworkErrorType.UNKNOWN
        )
    } else if (response is Resource.Error && response.message.contains(StatusCode.UnsupportedMediaType.code.toString())) {
        Resource.Error(
            BaseApplication.getString(R.string.err_msg_unsupported_media_file),
            NetworkErrorType.UNKNOWN
        )
    } else {
        response
    }
}

