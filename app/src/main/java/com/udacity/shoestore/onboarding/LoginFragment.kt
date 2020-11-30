package com.udacity.shoestore.onboarding

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
            clearInputs()
        }
        binding.emailEditText.addTextChangedListener(textWatcher)
        binding.editTextTextPassword.addTextChangedListener(textWatcher)

        //Passes Safe Args to determine Welcome message
        binding.loginButton.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(false, binding.emailEditText.text.toString()))
            clearInputs()
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(true, binding.emailEditText.text.toString()))
            clearInputs()
        }
        return binding.root
    }

    //Ensures that Login and Register buttons only Clickable if a edittexts not empty and a properly formatted email entered
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (binding.emailEditText.text.isNotEmpty() && binding.editTextTextPassword.text.isNotEmpty() && binding.emailEditText.text.isValidEmail()) {
                binding.loginButton.isEnabled = true
                binding.registerButton.isEnabled = true
            } else {
                binding.loginButton.isEnabled = false
                binding.registerButton.isEnabled = false
            }
        }
    }

    fun clearInputs() {
        binding.emailEditText.text.clear()
        binding.editTextTextPassword.text.clear()
    }

    // Checks if email is formatted correctly
    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}