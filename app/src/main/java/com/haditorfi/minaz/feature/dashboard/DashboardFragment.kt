package com.haditorfi.minaz.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.databinding.DashboardFragmentBinding
import org.koin.android.ext.android.inject

class DashboardFragment : BaseFragment<DashboardFragmentBinding>() {
    private val viewModel: DashboardViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        binding.apply {
            miAddCustomer.setOnClickListener {
                val action =
                    DashboardFragmentDirections.actionDashboardToAddCustomer(viewModel.customer)
                findNavController().navigate(action)
            }

            miProvideService.setOnClickListener {
                val action =
                    DashboardFragmentDirections.actionDashboardToAddProvideService(viewModel.service)
                findNavController().navigate(action)
            }

            miProduct.setOnClickListener {
                val action =
                    DashboardFragmentDirections.actionDashboardToAddProduct(viewModel.product)
                findNavController().navigate(action)
            }
        }
    }

    private fun initToolbar() {
        binding.include.toolbarTitleTv.text = getString(R.string.dashboard)
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DashboardFragmentBinding = DashboardFragmentBinding.inflate(inflater, container, false)
}