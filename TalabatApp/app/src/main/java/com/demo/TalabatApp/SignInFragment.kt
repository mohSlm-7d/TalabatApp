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
import kotlinx.android.synthetic.main.fragment_sign_in.*

@SuppressLint("ResourceType")
class SignInFragment : Fragment(R.id.signInFragement) {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val signUpLink = view.findViewById<TextView>(R.id.linkToSignUp)
        signUpLink.setOnClickListener {
            val signUpFragment = SignUpFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, signUpFragment)
                ?.commit()
        }


        // Sign in Components:
        val linkToSignUp: TextView? = view?.findViewById(R.id.linkToSignUp)
        val signInEmail: TextView? = view?.findViewById(R.id.editTextEmailSignIn)
        val signInPassword: EditText? = view?.findViewById(R.id.editTextPasswordSignIn)
        val signInButton: Button? = view?.findViewById(R.id.buttonSignIn)

        signInButton?.setOnClickListener{
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
