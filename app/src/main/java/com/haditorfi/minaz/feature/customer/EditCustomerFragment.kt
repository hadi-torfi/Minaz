package com.haditorfi.minaz.feature.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haditorfi.minaz.common.DATA_KEY
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.databinding.FragmentEditCustomerBinding

class EditCustomerFragment : Fragment() {
    lateinit var binding: FragmentEditCustomerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val customer = arguments?.getSerializable(DATA_KEY) as Customer
        binding.apply {
            txtUserName.text = customer.name
            txtUserMobile.text = customer.mobile
        }
    }
}