package com.cathay.homework.ui.plant

import androidx.hilt.lifecycle.ViewModelInject
import com.cathay.homework.model.repository.ZooRepository
import com.cathay.homework.ui.base.BaseViewModel

class PlantViewModel @ViewModelInject constructor(
    private var zooRepository: ZooRepository
) : BaseViewModel()