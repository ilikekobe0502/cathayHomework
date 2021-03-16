package com.cathay.homework.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cathay.homework.R
import com.cathay.homework.model.api.ApiResult.*
import com.cathay.homework.ui.base.BaseFragment
import com.cathay.homework.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.view.*

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbarUi()
        setupContentUi()

        layout_refresh.setOnRefreshListener {
            viewModel.getZoo()
        }

        viewModel.zooResult.observe(viewLifecycleOwner, {
            when (it) {
                is Loading -> layout_refresh.isRefreshing = true
                is Loaded -> layout_refresh.isRefreshing = false
                is Success -> {
                    it.result?.let { data -> homeAdapter.updateData(data) }
                    tv_data_empty.visibility = when {
                        homeAdapter.isEmpty() -> View.VISIBLE
                        else -> View.GONE
                    }
                }
                is Error -> onApiError(it.throwable)
            }
        })

        viewModel.getZoo()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    private fun setupToolbarUi() {
        layout_toolbar.tv_toolbar_title.text = getString(R.string.home_title)
    }

    private fun setupContentUi() {
        rv_home.also {
            it.setHasFixedSize(true)
            it.adapter = homeAdapter
        }
    }

    private val homeAdapter by lazy {
        HomeAdapter(homeFuncListener)
    }

    private val homeFuncListener by lazy {
        HomeFuncListener(
            onItemClick = {
                navigateTo(DetailFragment.createFragment(it))
            }
        )
    }
}