package com.bigint.birthdayreminder.data.repository

import com.bigint.birthdayreminder.BaseApplication
import com.bigint.birthdayreminder.R
import com.bigint.birthdayreminder.data.model.BaseApiResponse
import com.bigint.birthdayreminder.data.model.ServicesCategoriesData
import com.bigint.birthdayreminder.data.model.test.SeedsTestDto
import com.bigint.birthdayreminder.data.remote.ApiService
import com.bigint.birthdayreminder.data.remote.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getServicesData(): Resource<SeedsTestDto> {
        return try {
            val response = apiService.getServices()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(
                e.message
                    ?: BaseApplication.getString(R.string.err_msg_unknown_error_occured)
            )
        }
    }
}