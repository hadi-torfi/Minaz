package com.haditorfi.minaz.feature.staff

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
import com.haditorfi.minaz.data.staff.Staff
import com.haditorfi.minaz.databinding.StaffFragmentBinding
import org.koin.android.ext.android.inject


class StaffFragment : Fragment() {
    lateinit var binding: StaffFragmentBinding
    private val viewModel: StaffViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StaffFragmentBinding.inflate(inflater, container, false)
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
                StaffAdapter(requireContext(), it, IItemClickListener = { item, personnel ->
                    popUp(item, personnel)
                })

            binding.rvPersonnel.run {
                setHasFixedSize(true)
                adapter = viewAdapter
            }
        }
    }

    private fun popUp(view: View, staff: Staff) {
        val popup = PopupMenu(context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.options, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_menu -> {
                    goToAddOrEditFragment(staff, true)
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
                            viewModel.deleteStaff(staff)
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
        staff: Staff = Staff(),
        editModeTrue: Boolean = false
    ) {
        staff.activeEditMode = editModeTrue
        val action =
            StaffFragmentDirections.actionStaffToAddStaff(
                staff
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
        val p1 = Staff("مینا عبدالنبی", "09166424196", "تهران", MANAGER)
        val p2 = Staff("نازنین طرفی", "09352623050", "شوش", SECRETARY)
        val p3 = Staff("ساره بیات", "09352625553", "تهران", PERSONNEL)
        viewModel.insertStaff(p1, p2, p3)
    }
}