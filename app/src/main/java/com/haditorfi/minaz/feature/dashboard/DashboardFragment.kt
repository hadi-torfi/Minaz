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

class DashboardFragment : MyFragment() {
    lateinit var binding: DashboardFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnCustomerList.setOnClickListener {
                findNavController().navigate(R.id.action_dashboard_to_customer)
            }
            miAddCustomer.setOnClickListener {
                goToAddCustomerFragment()
            }
            miAddService.setOnClickListener {
                goToAddServiceFragment()
            }
        }

    }

    private fun goToAddServiceFragment() {
        val action =
            DashboardFragmentDirections.actionDashboardToAddService(
                Service("", 0, 0)
            )
        findNavController().navigate(action)
    }

    private fun goToAddCustomerFragment() {
        val action =
            DashboardFragmentDirections.actionDashboardToAddCustomer(
                Customer(0, "", "", "", false)
            )
        findNavController().navigate(action)
    }
}