package com.t.mediacorp2359pocs.presentation.oc2097_liveblog.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.t.mediacorp2359pocs.di.NetworkModule
import com.t.mediacorp2359pocs.presentation.oc2097_liveblog.detail.LiveBlogEventDetailResponse.Data.Event.KeyPoint
import kotlinx.coroutines.launch
import timber.log.Timber

class LiveBlogDetailViewModel : ViewModel() {

    private val mApiService = NetworkModule.createApiService()

    private val _liveKeyPointsLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val liveKeyPointsLoading: LiveData<Boolean>
        get() = _liveKeyPointsLoading

    private val _liveKeyPoints: MutableLiveData<List<KeyPoint>> by lazy {
        MutableLiveData<List<KeyPoint>>()
    }
    val liveKeyPoints: LiveData<List<KeyPoint>>
        get() = _liveKeyPoints

    private val _liveErrorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val liveErrorMessage: LiveData<String>
        get() = _liveErrorMessage

    fun getEventKeyPoints() {
        viewModelScope.launch {
            _liveKeyPointsLoading.postValue(true)
            try {
                val url = "https://data.24liveplus.com/v1/retrieve_server/x/event/2636807179789895648/"
                val response = mApiService.getLiveBlogEventDetails(url)
                _liveKeyPoints.postValue(response.data.event.keyPoints)
            } catch (ex: Exception) {
                Timber.e(ex)
                val errorMessage = ex.localizedMessage ?: "Something went wrong"
                _liveErrorMessage.postValue(errorMessage)
            } finally {
                _liveKeyPointsLoading.postValue(false)
            }
        }
    }
}