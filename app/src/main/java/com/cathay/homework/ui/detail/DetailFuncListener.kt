package com.cathay.homework.ui.detail

import com.cathay.homework.model.api.model.response.PlantItem

class DetailFuncListener(
        val onOpenWeb: (String) -> Unit = { },
        val onPlantItemClick: (PlantItem) -> Unit = { },
)