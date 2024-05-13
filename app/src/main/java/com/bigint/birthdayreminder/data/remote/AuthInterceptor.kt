package com.bigint.birthdayreminder.data.remote

import android.content.Context
import com.bigint.birthdayreminder.data.preferences.Prefs
import com.bigint.birthdayreminder.utils.Constants.Keys.KEY_AUTHENTICATION_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(@ApplicationContext private val context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token: String? = Prefs(context).getString(KEY_AUTHENTICATION_TOKEN, null)
        token?.let {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }
        return chain.proceed(request)
    }
}
