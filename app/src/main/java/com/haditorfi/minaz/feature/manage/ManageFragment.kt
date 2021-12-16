package com.haditorfi.minaz.feature.manage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.databinding.ManageFragmentBinding

class ManageFragment : BaseFragment<ManageFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // initToolbar()
        binding.apply {
            btnCustomerList.setOnClickListener {
                findNavController().navigate(R.id.action_manage_to_customer)
            }

            btnStaffList.setOnClickListener {
                findNavController().navigate(R.id.action_manage_to_staff)
            }

            btnProductList.setOnClickListener {
                findNavController().navigate(R.id.action_manage_to_product)
            }

            btnServiceList.setOnClickListener {
                findNavController().navigate(R.id.action_manage_to_service)
            }
        }
    }

    override fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.manage)
        }
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ManageFragmentBinding = ManageFragmentBinding.inflate(inflater, container, false)
}