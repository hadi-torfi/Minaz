package com.haditorfi.minaz.feature.services.provide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.invisible
import com.haditorfi.minaz.databinding.ServiceProvideDetailFragmentBinding

class DetailProvideServiceFragment : BaseFragment<ServiceProvideDetailFragmentBinding>() {
    private val args by navArgs<DetailProvideServiceFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // initToolbar()
        binding.apply {
            data = args.data
        }
    }

    override fun initToolbar() {
        binding.apply {
            include.apply {
                toolbarTitleTv.text = getString(R.string.service_list)
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