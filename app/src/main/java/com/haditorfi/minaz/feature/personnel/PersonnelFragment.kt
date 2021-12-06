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
import com.haditorfi.minaz.common.MANAGER
import com.haditorfi.minaz.common.PERSONNEL
import com.haditorfi.minaz.common.SECRETARY
import com.haditorfi.minaz.data.personnel.Personnel
import com.haditorfi.minaz.databinding.PersonnelFragmentBinding
import org.koin.android.ext.android.inject


class PersonnelFragment : Fragment() {
    lateinit var binding: PersonnelFragmentBinding
    private val viewModel: PersonnelViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonnelFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.include.toolbarBtn.setOnClickListener {
            goToAddOrEditFragment()
        }

        viewModel.allPersonnel.observe(viewLifecycleOwner) {
            if (it.isEmpty()) createPersonnelAndSkill()

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
        personnel: Personnel = Personnel(),
        editModeTrue: Boolean = false
    ) {
        personnel.activeEditMode = editModeTrue
        val action =
            PersonnelFragmentDirections.actionPersonnelToAddPersonnel(
                personnel
            )
        findNavController().navigate(action)
    }

    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.personnel_list)
            include.toolbarBtn.text = getString(R.string.personnel_new)
            include.toolbarBackBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun createPersonnelAndSkill() {
        val p1 = Personnel("مینا عبدالنبی", "09166424196", "تهران", MANAGER)
        val p2 = Personnel("نازنین طرفی", "09352623050", "شوش", SECRETARY)
        val p3 = Personnel("ساره بیات", "09352625553", "تهران", PERSONNEL)
        viewModel.insertPersonnel(p1,p2,p3)
    }
}