package com.haditorfi.minaz.feature.services.provide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.gone
import com.haditorfi.minaz.common.invisible
import com.haditorfi.minaz.data.service.provide.Provides
import com.haditorfi.minaz.databinding.ServiceProvideDetailFragmentBinding

class DetailProvideServiceFragment : BaseFragment<ServiceProvideDetailFragmentBinding>() {
    private val args by navArgs<DetailProvideServiceFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            data = args.data
            val mData = data as Provides
            for ((index, service) in mData.provideService.services.withIndex()) {
                txtServicesName.append("${index + 1} - ${service.name} \n")
                txtServicesPrice.append("${service.strPrice} \n")
            }
        }
    }

    override fun initToolbar() {
        binding.apply {
            include.apply {
                toolbarTitleTv.text = getString(R.string.detail_service_provided)
                toolbarBtn.invisible()
                toolbarBackBtn.setOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ServiceProvideDetailFragmentBinding =
        ServiceProvideDetailFragmentBinding.inflate(inflater, container, false)
}