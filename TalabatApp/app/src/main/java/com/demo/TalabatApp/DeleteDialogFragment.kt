package com.demo.foodorderanddeliveryappkotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment

class DeleteDialogFragment: DialogFragment(R.layout.fragment_delete_dialog)  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        val deleteButton : Button =view?.findViewById(R.id.deleteButton)
        val cancelDeleteButton : Button =view?.findViewById(R.id.cancelDeleteButton)


        deleteButton.setOnClickListener{
            // DB delete here.
            dismiss()
        }

        cancelDeleteButton.setOnClickListener{
            dismiss()
        }
    }
}