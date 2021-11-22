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
        binding.include.toolbarTitleTv.text = getString(R.string.manage)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            btnCustomerList.setOnClickListener {
                findNavController().navigate(R.id.action_manage_to_customer)
            }

            btnPersonnelList.setOnClickListener {
                findNavController().navigate(R.id.action_manage_to_personnel)
            }
        }
    }
}