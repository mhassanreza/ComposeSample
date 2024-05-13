package com.bigint.birthdayreminder.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigint.birthdayreminder.BaseApplication
import com.bigint.birthdayreminder.R
import com.bigint.birthdayreminder.data.model.BaseApiResponse
import com.bigint.birthdayreminder.data.model.ServicesCategoriesData
import com.bigint.birthdayreminder.data.model.test.SeedsTestDto
import com.bigint.birthdayreminder.data.remote.Resource
import com.bigint.birthdayreminder.data.remote.filterResource
import com.bigint.birthdayreminder.data.repository.MainRepository
import com.bigint.birthdayreminder.enums.NetworkErrorType
import com.bigint.birthdayreminder.utils.isInternetConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository, private val application: Application
) : ViewModel() {

    private val _servicesData =
        MutableLiveData<Resource<SeedsTestDto>>()
    val servicesData: LiveData<Resource<SeedsTestDto>> =
        _servicesData

    fun callServicesDataApi() {
        viewModelScope.launch {
            _servicesData.value = Resource.Loading
            if (!isInternetConnected(application.applicationContext)) {
                _servicesData.value = Resource.Error(
                    BaseApplication.getString(R.string.err_msg_internet_not_connected),
                    NetworkErrorType.NO_INTERNET
                )
            } else {
                val response = mainRepository.getServicesData()
                _servicesData.value = filterResource(response)
            }
        }
    }
}
