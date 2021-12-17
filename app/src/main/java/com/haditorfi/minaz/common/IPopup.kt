package com.haditorfi.minaz.common

import android.content.Context
import android.view.MenuInflater
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.haditorfi.minaz.R

interface IPopup<T> {
    fun popUp(
        context: Context,
        item: View,
        mClass: T
    ) {
        val popup = android.widget.PopupMenu(context, item)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.options, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_menu -> {
                    goToAddOrEditFromIPopup(mClass,true)
                    return@setOnMenuItemClickListener true
                }
                R.id.delete_menu -> {
                    MaterialAlertDialogBuilder(item.context)
                        .setTitle(context.getString(R.string.delete_message))
                        .setIcon(R.drawable.ic_error)
                        .setNeutralButton(context.getString(R.string.cancel)) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton(context.getString(R.string.accept)) { _, _ ->
                            deleteFromIPopup(mClass)
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

    fun deleteFromIPopup(myClass: T)

    fun goToAddOrEditFromIPopup(myClass: T, editModeTrue: Boolean = false)

}