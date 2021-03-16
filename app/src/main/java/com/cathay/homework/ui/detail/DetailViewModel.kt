package com.cathay.homework.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cathay.homework.model.api.ApiResult
import com.cathay.homework.model.api.model.response.PlantItem
import com.cathay.homework.model.repository.ZooRepository
import com.cathay.homework.ui.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private var zooRepository: ZooRepository
) : BaseViewModel() {

    private val _plantResult = MutableLiveData<ApiResult<ArrayList<PlantItem>?>>()
    val plantResult: LiveData<ApiResult<ArrayList<PlantItem>?>> = _plantResult

    fun getPlant() {
        viewModelScope.launch {
            zooRepository.getPlant()
                .onStart { _plantResult.value = ApiResult.loading() }
                .catch { e -> _plantResult.value = ApiResult.error(e) }
                .onCompletion { _plantResult.value = ApiResult.loaded() }
                .collect { _plantResult.value = ApiResult.success(it) }
        }
    }
}