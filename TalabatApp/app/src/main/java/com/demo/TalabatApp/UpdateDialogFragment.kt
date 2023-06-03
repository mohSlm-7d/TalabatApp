package com.demo.foodorderanddeliveryappkotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class UpdateDialogFragment: DialogFragment(R.layout.fragment_update_dialog)  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val updateFirstName: EditText = view?.findViewById(R.id.updateFirstName)
        val updateLastName: EditText = view?.findViewById(R.id.updateLastName)
        val updateEmail: EditText = view?.findViewById(R.id.updateEmail)
        val updatePassword: EditText = view?.findViewById(R.id.updatePassword)
        val updatePhoneNumber: EditText = view?.findViewById(R.id.updatePhoneNumber)

        val updateButton: Button = view?.findViewById(R.id.updateButton)
        val cancelUpdateButton: Button = view?.findViewById(R.id.cancelUpdateButton)


        updateButton.setOnClickListener({
            // DB update here.
            dismiss()
        })

        cancelUpdateButton.setOnClickListener{
            dismiss()
        }

    }
}