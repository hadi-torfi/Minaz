package com.haditorfi.minaz.feature.personnel

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
import com.haditorfi.minaz.data.personnel.Personnel
import com.haditorfi.minaz.databinding.PersonnelFragmentBinding
import org.koin.android.ext.android.inject


class PersonnelFragment : Fragment() {
    lateinit var binding: PersonnelFragmentBinding
    private val viewModel: PersonnelViewModel by inject()
    private val personnel: Personnel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonnelFragmentBinding.inflate(inflater, container, false)

        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.personnel_list)
            include.toolbarBtn.text = getString(R.string.personnel_new)
            include.backBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.include.toolbarBtn.setOnClickListener {
            goToAddOrEditFragment(personnel)
        }

        viewModel.allPersonnel.observe(viewLifecycleOwner) {
            val viewAdapter =
                PersonnelAdapter(requireContext(), it, IItemClickListener = { item, personnel ->
                    popUp(item, personnel)

                })

            binding.rvPersonnel.run {
                setHasFixedSize(true)
                adapter = viewAdapter
            }
        }
    }

    private fun popUp(view: View, personnel: Personnel) {
        val popup = PopupMenu(context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.options, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_menu -> {
                    goToAddOrEditFragment(personnel, true)
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
                            viewModel.deletePersonnel(personnel)
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
        personnel: Personnel,
        editModeTrue: Boolean = false
    ) {
        personnel.activeEditMode = editModeTrue
        val action =
            PersonnelFragmentDirections.actionPersonnelToAddPersonnel(
                personnel
            )
        findNavController().navigate(action)
    }
}