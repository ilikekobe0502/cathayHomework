package com.cathay.homework.ui.home

import com.cathay.homework.model.api.model.response.ZooItem

class HomeFuncListener(
        val onItemClick: (ZooItem) -> Unit = { },
)