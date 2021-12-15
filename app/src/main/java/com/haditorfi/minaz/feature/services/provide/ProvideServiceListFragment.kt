package com.haditorfi.minaz.feature.services.provide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.data.service.provide.Provides
import com.haditorfi.minaz.databinding.ServiceProvideListFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class ProvideServiceListFragment : BaseFragment<ServiceProvideListFragmentBinding>() {
    private val viewModel: ProvideServiceViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        binding.apply {
            viewModel.allProvideService.observe(viewLifecycleOwner) {
                if (it.isEmpty()) createProvideService()
                val serviceViewAdapter =
                    ProvideServiceAdapter(
                        requireContext(),
                        it
                    ) { item, provides ->
                        when (item.id) {
                            R.id.item_service_layout -> goToDetailFragment(provides as Provides)
                            R.id.btn_more -> popUp(item, provides as ProvideService)
                        }
                    }
                rvProvideService.adapter = serviceViewAdapter
            }

            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFragment()
            }
        }
    }


    private fun popUp(view: View, provideService: ProvideService) {
        val popup = PopupMenu(context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.options, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_menu -> {
                    goToAddOrEditFragment(provideService, true)
                    return@setOnMenuItemClickListener true
                }
                R.id.delete_menu -> {
                    MaterialAlertDialogBuilder(view.context)
                        .setTitle(resources.getString(R.string.delete_message))
                        .setIcon(R.drawable.ic_error)
                        .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                            viewModel.deleteProvideService(provideService)
                        }
                        .show()
                    return@setOnMenuItemClickListener true
                }
                else ->
                    return@setOnMenuItemClickListener false
            }
        }
        popup.show()
    }

    private fun goToDetailFragment(provideService: Provides) {
        val action =
            ProvideServiceListFragmentDirections.actionProvideServiceListToDetailProvideService(
                provideService
            )
        findNavController().navigate(action)
    }

    private fun goToAddOrEditFragment(
        provideService: ProvideService = ProvideService(),
        editModeTrue: Boolean = false
    ) {
        provideService.activeEditMode = editModeTrue
        val action =
            ProvideServiceListFragmentDirections.actionProvideServiceListToAddProvideService(
                provideService
            )
        findNavController().navigate(action)
    }

    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.service_provided)
            include.toolbarBtn.visibility = View.VISIBLE
            include.toolbarBtn.text = getString(R.string.service_provide)
        }
    }

    private fun createProvideService() {
        val s1 = Service("serv1", "5000")
        val s2 = Service("serv2", "1000")
        val s3 = Service("serv3", "32000")
        val s4 = Service("serv4", "86000")

        val p1 =
            ProvideService(1, 2, listOf(s1, s2), Date(), "120000", "20000", "100000", "توضیحات")
        val p2 = ProvideService(2, 3, listOf(s4, s3), Date(), "80000", "30000", "50000", "توضیح")
        viewModel.insertProvideService(p1)
        viewModel.insertProvideService(p2)
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ServiceProvideListFragmentBinding =
        ServiceProvideListFragmentBinding.inflate(inflater, container, false)
}