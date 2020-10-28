package com.t.mediacorp2359pocs.presentation.oc2097_liveblog.keypoints

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.t.mediacorp2359pocs.di.NetworkModule
import com.t.mediacorp2359pocs.presentation.oc2097_liveblog.detail.LiveBlogEventDetailResponse.Data.Event
import kotlinx.coroutines.launch
import timber.log.Timber

class LiveBlogEventPreviewViewModel : ViewModel() {

    private val mApiService = NetworkModule.createApiService()

    private val _liveLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val liveLoading: LiveData<Boolean>
        get() = _liveLoading

    private val _liveEvent: MutableLiveData<Event> by lazy {
        MutableLiveData<Event>()
    }
    val liveEvent: LiveData<Event>
        get() = _liveEvent

    private val _liveError: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val liveError: LiveData<String>
        get() = _liveError

    fun getEvent() {
        viewModelScope.launch {
            _liveLoading.postValue(true)
            try {
                val url = "https://data.24liveplus.com/v1/retrieve_server/x/event/2636807179789895648/"
                val response = mApiService.getLiveBlogEventDetails(url)
                _liveEvent.postValue(response.data.event)
            } catch (ex: Exception) {
                Timber.e(ex)
                val error = ex.localizedMessage ?: "Something went wrong"
                _liveError.postValue(error)
            } finally {
                _liveLoading.postValue(false)
            }
        }
    }
}