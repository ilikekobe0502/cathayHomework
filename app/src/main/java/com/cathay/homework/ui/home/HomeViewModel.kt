package com.cathay.homework.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cathay.homework.model.api.ApiResult
import com.cathay.homework.model.api.model.response.ZooItem
import com.cathay.homework.model.repository.ZooRepository
import com.cathay.homework.ui.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private var zooRepository: ZooRepository
) : BaseViewModel() {

    private val _zooResult = MutableLiveData<ApiResult<ArrayList<ZooItem>?>>()
    val zooResult: LiveData<ApiResult<ArrayList<ZooItem>?>> = _zooResult

    fun getZoo() {
        viewModelScope.launch {
            zooRepository.getZoo()
                .onStart { _zooResult.value = ApiResult.loading() }
                .catch { e -> _zooResult.value = ApiResult.error(e) }
                .onCompletion { _zooResult.value = ApiResult.loaded() }
                .collect { _zooResult.value = ApiResult.success(it) }
        }
    }
}