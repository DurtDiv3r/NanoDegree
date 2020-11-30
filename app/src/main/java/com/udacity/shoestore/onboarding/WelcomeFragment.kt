package com.udacity.shoestore.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var greeting: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        //Uses Safe Args to Welcome new or Existing user

        val args = WelcomeFragmentArgs.fromBundle(requireArguments())
        val name = args.email.split("@")
        greeting = if (args.newUser) {
            "Welcome to our Store ${name[0]}"
        } else {
            "Welcome Back ${name[0]}"
        }
        binding.titleTextview.text = greeting
        binding.welcomeButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_instructionsFragment)
        )
        return binding.root
    }
}