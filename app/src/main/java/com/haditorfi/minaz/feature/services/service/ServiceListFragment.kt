package com.haditorfi.minaz.feature.services.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.IPopup
import com.haditorfi.minaz.data.service.service.Service
import com.haditorfi.minaz.databinding.ServiceListFragmentBinding
import org.koin.android.ext.android.inject

class ServiceListFragment : BaseFragment<ServiceListFragmentBinding>(), IPopup<Service> {
    private val viewModel: ServiceViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.allService.observe(viewLifecycleOwner) {
                val serviceViewAdapter =
                    ServiceAdapter(requireContext(), it) { item, service ->
                        popUp(item, service)
                    }

                rvService.run {
                    setHasFixedSize(true)
                    adapter = serviceViewAdapter
                }

            }
            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFromIPopup(Service())
            }
        }
    }

    override fun initToolbar() {
        binding.include.apply {
            toolbarTitleTv.text = getString(R.string.service)
            toolbarBtn.text = getString(R.string.service_new)
            toolbarBackBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ServiceListFragmentBinding =
        ServiceListFragmentBinding.inflate(inflater, container, false)

    override fun deleteFromIPopup(myClass: Service) {
        viewModel.deleteService(myClass)
    }

    override fun goToAddOrEditFromIPopup(myClass: Service, editModeTrue: Boolean) {
        myClass.activeEditMode = editModeTrue
        val action =
            ServiceListFragmentDirections.actionServiceToAddService(
                myClass
            )
        findNavController().navigate(action)
    }

}