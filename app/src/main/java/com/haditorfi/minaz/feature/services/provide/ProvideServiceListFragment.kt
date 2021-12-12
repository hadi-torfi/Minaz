package com.haditorfi.minaz.feature.services.provide

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
import com.haditorfi.minaz.data.service.provide.Provides
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.databinding.ServiceProvideListFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class ProvideServiceListFragment : Fragment() {
    lateinit var binding: ServiceProvideListFragmentBinding
    private val viewModel: ProvideServiceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ServiceProvideListFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.allProvideService.observe(viewLifecycleOwner) {
                if (it.isEmpty()) createProvideService()
                val serviceViewAdapter =
                    ProvideServiceAdapter(
                        requireContext(),
                        it,
                        IItemClickListener = { item, provides ->
                            popUp(item, provides)
                        })
                rvProvideService.adapter = serviceViewAdapter
            }
            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFragment()
            }
        }
    }

    private fun popUp(view: View, provides: Provides) {
        val popup = PopupMenu(context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.options, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_menu -> {
                    goToAddOrEditFragment(provides, true)
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
                            viewModel.deleteProvideService(provides)
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
        val p1 = ProvideService(1, 2, 3, Date(), "120000","20000","100000", "توضیحات")
        val p2 = ProvideService(2, 3, 2, Date(), "80000","30000","50000", "توضیح")
        viewModel.insertProvideService(p1)
        viewModel.insertProvideService(p2)
    }
}