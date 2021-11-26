package com.haditorfi.minaz.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.MyFragment
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.databinding.DashboardFragmentBinding
import org.koin.android.ext.android.inject

class DashboardFragment : MyFragment() {
    lateinit var binding: DashboardFragmentBinding
    private val customerInject: Customer by inject()
    private val serviceInject: Service by inject()

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
                    DashboardFragmentDirections.actionDashboardToAddCustomer(customerInject)
                findNavController().navigate(action)
            }

            miAddService.setOnClickListener {
                val action = DashboardFragmentDirections.actionDashboardToAddService(serviceInject)
                findNavController().navigate(action)
            }
        }
    }

    private fun initToolbar() {
        binding.include.toolbarTitleTv.text = getString(R.string.dashboard)
    }
}