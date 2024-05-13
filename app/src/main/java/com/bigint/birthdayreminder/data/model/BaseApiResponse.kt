package com.bigint.birthdayreminder.data.model

data class BaseApiResponse<T>(
    var data: T,
    val errorCode: Any,
    val errorMessage: Any?,
    val message: Any?,
    val messageAr: Any,
    val modelState: Any,
    var successed: Boolean
)