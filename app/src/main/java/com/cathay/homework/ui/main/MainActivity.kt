package com.cathay.homework.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.cathay.homework.R
import com.cathay.homework.ui.base.BaseActivity
import com.cathay.homework.ui.home.HomeFragment
import com.cathay.homework.utils.utility.GeneralUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateTo(HomeFragment())
    }

    override fun onBackPressed() {
        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        takeIf { backStackEntryCount == 0 }?.run {
            takeIf { viewModel.needCloseApp }?.run { finish() }
                ?: run {
                    viewModel.startBackExitAppTimer()
                    GeneralUtils.showToast(this, getString(R.string.press_again_exit))
                }
        } ?: run { supportFragmentManager.popBackStack() }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}