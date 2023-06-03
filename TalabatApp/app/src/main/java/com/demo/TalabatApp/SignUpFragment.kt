package com.demo.foodorderanddeliveryappkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("ResourceType")
class SignUpFragment : Fragment(R.id.signUpFragement) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val signInLink = view.findViewById<TextView>(R.id.linkToSignIn)
        signInLink.setOnClickListener {
            val signInFragment = SignInFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, signInFragment)
                ?.commit()
        }

        // Sign Up Components:
        val firstName: EditText? = view?.findViewById(R.id.FirstName)
        val lastName: EditText? = view?.findViewById(R.id.editTextLastName)
        val signUpEmail: EditText? = view?.findViewById(R.id.editTextEmail)
        val signUpPassword: EditText? = view?.findViewById(R.id.editTextPassword)
        val phoneNumber: EditText? = view?.findViewById(R.id.editTextPhoneNumber)
        val signUpButton: TextView? = view?.findViewById(R.id.buttonSignUp)
        val linkToSignIn: TextView? = view?.findViewById(R.id.linkToSignIn)


        signUpButton?.setOnClickListener{
            val fragmentManager = requireActivity().supportFragmentManager
            val currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainer)
            if (currentFragment is SignUpFragment) {
                fragmentManager.beginTransaction()
                    .remove(currentFragment)
                    .commit()
            }
            val main:MainActivity = getActivity() as MainActivity
            var rest_list = main.getRestaurantData()
            main.initRecyclerView(rest_list)

        }


        return view
    }
}
