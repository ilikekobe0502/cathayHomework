package com.cathay.homework.ui.plant

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cathay.homework.R
import com.cathay.homework.model.api.model.response.PlantItem
import com.cathay.homework.ui.base.BaseFragment
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_plant.*
import kotlinx.android.synthetic.main.toolbar.view.*

@AndroidEntryPoint
class PlantFragment : BaseFragment() {

    companion object {
        private const val KEY_ITEM = "KEY_ITEM"
        fun createFragment(item: PlantItem): Fragment {
            val bundle = Bundle().also { it.putParcelable(KEY_ITEM, item) }
            return PlantFragment().also { it.arguments = bundle }
        }
    }

    private val viewModel: PlantViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = arguments?.getParcelable<PlantItem>(KEY_ITEM)

        item?.let {
            setupToolbarUi(it)
            setupContentUi(it)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_plant
    }

    private fun setupToolbarUi(item: PlantItem) {
        layout_toolbar.tv_toolbar_title.text = item.nameCh

        layout_toolbar.toolbar.also {
            it.navigationIcon = ContextCompat.getDrawable(
                    requireContext(), R.drawable.ic_baseline_arrow_back_24
            )
            it.setNavigationOnClickListener { onBackPressed() }
        }
    }

    private fun setupContentUi(item: PlantItem) {
        Glide.with(requireContext())
                .load(item.pic01Url)
                .placeholder(R.drawable.ic_picture_small_empty)
                .error(R.drawable.ic_picture_small_empty)
                .into(iv_plant)

        item.apply {
            tv_cn_name.text = nameCh
            tv_en_name.text = nameEn
            tv_also_known.text = alsoKnown
            tv_brief.text = brief
            tv_feature.text = feature
            tv_function.text = functionApplication
            tv_update_time.text = update
        }
    }
}