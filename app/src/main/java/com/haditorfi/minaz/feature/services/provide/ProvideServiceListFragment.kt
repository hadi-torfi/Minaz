package com.haditorfi.minaz.feature.services.provide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.IPopup
import com.haditorfi.minaz.common.visible
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.data.service.provide.Provides
import com.haditorfi.minaz.databinding.ServiceProvideListFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProvideServiceListFragment : BaseFragment<ServiceProvideListFragmentBinding>(),
    IPopup<ProvideService> {
    private val viewModel: ProvideServiceViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.allProvideService.observe(viewLifecycleOwner) {
                val serviceViewAdapter =
                    ProvideServiceAdapter(
                        requireContext(),
                        it
                    ) { item, provides ->
                        when (item.id) {
                            R.id.item_service_layout -> goToDetailFragment(provides as Provides)
                            R.id.btn_more -> popUp(
                                requireContext(),
                                item,
                                provides as ProvideService
                            )
                        }
                    }
                rvProvideService.run {
                    setHasFixedSize(true)
                    adapter = serviceViewAdapter
                }
            }

            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFromIPopup(ProvideService())
            }
        }
    }

    private fun goToDetailFragment(provideService: Provides) {
        val action =
            ProvideServiceListFragmentDirections.actionProvideServiceListToDetailProvideService(
                provideService
            )
        findNavController().navigate(action)
    }

    override fun initToolbar() {
        binding.apply {
            include.apply {
                toolbarTitleTv.text = getString(R.string.service_provided)
                toolbarBtn.visible()
                toolbarBtn.text = getString(R.string.service_provide)
            }
        }
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ServiceProvideListFragmentBinding =
        ServiceProvideListFragmentBinding.inflate(inflater, container, false)

    override fun deleteFromIPopup(myClass: ProvideService) {
        viewModel.deleteProvideService(myClass)
    }

    override fun goToAddOrEditFromIPopup(myClass: ProvideService, editModeTrue: Boolean) {
        myClass.activeEditMode = editModeTrue
        val action =
            ProvideServiceListFragmentDirections.actionProvideServiceListToAddProvideService(
                myClass
            )
        findNavController().navigate(action)
    }
}