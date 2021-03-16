package com.cathay.homework.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cathay.homework.R
import com.cathay.homework.model.api.ApiResult.*
import com.cathay.homework.model.api.model.response.ZooItem
import com.cathay.homework.ui.base.BaseFragment
import com.cathay.homework.ui.plant.PlantFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.layout_toolbar
import kotlinx.android.synthetic.main.toolbar.view.*

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    companion object {
        private const val KEY_ITEM = "KEY_ITEM"
        fun createFragment(item: ZooItem): Fragment {
            val bundle = Bundle().also { it.putParcelable(KEY_ITEM, item) }
            return DetailFragment().also { it.arguments = bundle }
        }
    }

    private val viewModel: DetailViewModel by viewModels()

    private var detailAdapter: DetailAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = arguments?.getParcelable<ZooItem>(KEY_ITEM)

        item?.let {
            setupToolbarUi(it)
            setupContentUi(it)
        }

        tv_retry.setOnClickListener {
            viewModel.getPlant()
        }

        viewModel.plantResult.observe(viewLifecycleOwner, {
            when (it) {
                is Loading -> {
                    layout_retry.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                }
                is Loaded -> progress.visibility = View.GONE
                is Success -> {
                    it.result?.let { data -> detailAdapter?.updatePlantsData(data) }
                    layout_retry.visibility = when {
                        detailAdapter?.isPlantsEmpty() == true -> View.VISIBLE
                        else -> View.GONE
                    }
                }
                is Error -> {
                    layout_retry.visibility = View.VISIBLE
                    onApiError(it.throwable)
                }
            }
        })

        viewModel.getPlant()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    private fun setupToolbarUi(item: ZooItem) {
        layout_toolbar.tv_toolbar_title.text = item.name

        layout_toolbar.toolbar.also {
            it.navigationIcon = ContextCompat.getDrawable(
                requireContext(), R.drawable.ic_baseline_arrow_back_24
            )
            it.setNavigationOnClickListener { onBackPressed() }
        }
    }

    private fun setupContentUi(item: ZooItem) {
        detailAdapter = DetailAdapter(item, detailFuncListener)

        rv_detail.also {
            it.setHasFixedSize(true)
            it.adapter = detailAdapter
        }
    }

    private val detailFuncListener by lazy {
        DetailFuncListener(
            onOpenWeb = { url ->
                Intent().also {
                    it.action = Intent.ACTION_VIEW
                    it.data = Uri.parse(url)
                    startActivity(it)
                }
            },
            onPlantItemClick = {
                navigateTo(PlantFragment.createFragment(it))
            }
        )
    }
}