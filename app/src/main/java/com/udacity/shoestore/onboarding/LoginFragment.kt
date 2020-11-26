package com.udacity.shoestore.onboarding

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        if(binding.emailEditText.text.isNotEmpty() || binding.editTextTextPassword.text.isNotEmpty()) {
            binding.editTextTextPassword.setText("")
            binding.emailEditText.setText("")
        }
        binding.emailEditText.addTextChangedListener(textWatcher)
        binding.editTextTextPassword.addTextChangedListener(textWatcher)

        binding.loginButton.setOnClickListener{
            binding.emailEditText.text.clear()
            binding.editTextTextPassword.text.clear()
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }

        binding.registerButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
        return binding.root
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (binding.emailEditText.text.isNotEmpty() && binding.editTextTextPassword.text.isNotEmpty()) {
                binding.loginButton.isEnabled = true
                binding.registerButton.isEnabled = true
            } else {
                binding.loginButton.isEnabled = false
                binding.registerButton.isEnabled = false
            }
        }
    }
}