package com.haditorfi.minaz.feature.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.databinding.OrderFragmentBinding

class OrderFragment : BaseFragment<OrderFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): OrderFragmentBinding = OrderFragmentBinding.inflate(inflater, container, false)

    override fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.orders)
        }
    }
}