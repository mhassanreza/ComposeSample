package com.bigint.birthdayreminder.data.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response

import javax.inject.Inject

class HeaderInterceptor @Inject constructor(@ApplicationContext private val context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
//        val myPositions = Prefs(context).getString(
//            Constants.Keys.KEY_MY_POSITIONS, null
//        )
//        val positionsList = myPositions?.fromJsonStringToList<EServicesPositionDto>()
//        positionsList.let { list ->
//            val result = list?.firstOrNull { it.isDefault } ?: list?.first()
//            val positionId = result?.id.toString()
//            positionId.let {
//                requestBuilder.addHeader("positionId", it).build()
//            }
//        }
//        val language: String =
//            if (BaseApplication.getCurrentLanguage() == Constants.Keys.LANGUAGE_ENGLISH) {
//                "en-US"
//            } else {
//                "ar-AE"
//            }
//        language.let {
//            requestBuilder.addHeader(
//                "Language", it
//            )
//        }
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}



