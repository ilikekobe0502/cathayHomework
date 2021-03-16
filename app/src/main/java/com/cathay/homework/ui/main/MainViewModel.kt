package com.cathay.homework.ui.main

import androidx.lifecycle.viewModelScope
import com.cathay.homework.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    var needCloseApp = false // 判斷是否需要離開 app

    /**
     * 按下 back 離開的 Timer
     */
    fun startBackExitAppTimer() {
        needCloseApp = true
        viewModelScope.launch {
            for (second in 2 downTo 0) {
                delay(1000)
            }
            needCloseApp = false
        }
    }
}