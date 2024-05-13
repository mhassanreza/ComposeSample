package com.bigint.birthdayreminder.data.remote

import com.bigint.birthdayreminder.data.model.test.SeedsTestDto
import retrofit2.http.GET

interface ApiService {
    @GET(ApiURL.URL_SERVICES)
    suspend fun getServices(): SeedsTestDto
}