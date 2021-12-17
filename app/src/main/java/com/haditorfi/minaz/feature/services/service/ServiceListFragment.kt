package com.haditorfi.minaz.feature.services.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.IPopup
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.databinding.ServiceListFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ServiceListFragment : BaseFragment<ServiceListFragmentBinding>(), IPopup<Service> {
    private val viewModel: ServiceViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.allService.observe(viewLifecycleOwner) {
                if (it.isEmpty()) createService()
                val serviceViewAdapter =
                    ServiceAdapter(requireContext(), it, IItemClickListener = { item, service ->
                        popUp(requireContext(), item, service)
                    })
                rvService.adapter = serviceViewAdapter
            }
            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFromIPopup(Service())
            }
        }
    }

    override fun initToolbar() {
        binding.apply {
            include.apply {
                toolbarTitleTv.text = getString(R.string.service)
                toolbarBtn.visibility = View.VISIBLE
                toolbarBtn.text = getString(R.string.service_new)
            }
        }
    }

    private fun createService() {
        val s1 = Service("اپیلاسیون تمام بدن", "180000")
        val s2 = Service("شمع صورت", "30000")
        val s3 = Service("اپیلاسیون دست و پا", "130000")
        viewModel.insertService(s1, s2, s3)
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