package com.haditorfi.minaz.feature.services.provide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.databinding.ServiceProvideAddFragmentBinding

class AddProvideServiceFragment : BaseFragment<ServiceProvideAddFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ServiceProvideAddFragmentBinding =
        ServiceProvideAddFragmentBinding.inflate(inflater, container, false)
}