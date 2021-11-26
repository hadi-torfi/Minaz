package com.haditorfi.minaz.feature.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.databinding.ServiceFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ServiceFragment : Fragment() {
    lateinit var binding: ServiceFragmentBinding
    private val viewModel: ServiceViewModel by viewModel()
    private val serviceInject: Service by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ServiceFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.allService.observe(viewLifecycleOwner) {
                if (it.isEmpty()) createService()
                val serviceViewAdapter =
                    ServiceAdapter(requireContext(), it, IItemClickListener = { item, service ->
                        popUp(item, service)
                    })
                rvService.adapter = serviceViewAdapter
            }
            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFragment(serviceInject)
            }
        }
    }

    private fun popUp(view: View, service: Service) {
        val popup = PopupMenu(context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.options, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_menu -> {
                    goToAddOrEditFragment(service, true)
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
                            viewModel.deleteService(service)
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

    private fun goToAddOrEditFragment(
        service: Service,
        editModeTrue: Boolean = false
    ) {
        service.activeEditMode = editModeTrue
        val action =
            ServiceFragmentDirections.actionServiceToAddService(
                service
            )
        findNavController().navigate(action)
    }

    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.service)
            include.toolbarBtn.visibility = View.VISIBLE
            include.toolbarBtn.text = getString(R.string.service_new)
        }
    }

    private fun createService() {
        val s1 = Service("اپیلاسیون تمام بدن", "180000")
        val s2 = Service("شمع صورت", "30000")

        viewModel.insertService(s1)
        viewModel.insertService(s2)
    }

}