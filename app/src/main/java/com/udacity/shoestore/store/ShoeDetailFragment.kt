package com.udacity.shoestore.store

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ShoeViewModel


class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.shoeViewModel = shoeViewModel
        binding.lifecycleOwner = this
        setupTextWatchers()

        binding.saveButton.setOnClickListener {
            shoeViewModel.add()
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
            shoeViewModel.resetNav()
        }

        shoeViewModel.navigate.observe(viewLifecycleOwner, Observer { saved ->
            if (saved) {
                findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
                shoeViewModel.resetNav()
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun setupTextWatchers() {
        binding.nameInput.addTextChangedListener(textWatcher)
        binding.shoeSizeInput.addTextChangedListener(textWatcher)
        binding.companyInput.addTextChangedListener(textWatcher)
        binding.descriptionInput.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.saveButton.isEnabled =
                binding.nameInput.text.isNotEmpty() && binding.shoeSizeInput.text.isNotEmpty() && binding.companyInput.text.isNotEmpty() && binding.descriptionInput.text.isNotEmpty()
        }
    }
}