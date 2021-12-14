package com.haditorfi.minaz.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.MyFragment
import com.haditorfi.minaz.databinding.DashboardFragmentBinding
import org.koin.android.ext.android.inject

class DashboardFragment : MyFragment() {
    lateinit var binding: DashboardFragmentBinding
    private val viewModel: DashboardViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}