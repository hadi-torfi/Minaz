package com.haditorfi.minaz.feature.manage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.MyFragment
import com.haditorfi.minaz.databinding.ManageFragmentBinding

class ManageFragment : MyFragment() {
    lateinit var binding: ManageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ManageFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.manage)
        }
    }
}