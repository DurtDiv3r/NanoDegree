package com.udacity.shoestore.store

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeViewModel

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        setHasOptionsMenu(true)

        binding.shoeViewModel = shoeViewModel
        binding.lifecycleOwner = this
        binding.fab.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoeListFragment_to_shoeDetailFragment)
        )

        shoeViewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            val itemLayout: LinearLayout = binding.shoeCardLayout.findViewById(R.id.shoe_card_layout)
            if (shoeList.size > 0) {
                if (binding.noItems.isVisible) {
                    binding.noItems.visibility = View.GONE
                }
                for (shoe in shoeList) {
                    var addShoe: Shoe? = shoe.value

                    val view: View = layoutInflater.inflate(R.layout.shoe_layout, itemLayout, false)
                    val shoeNameTextView: TextView = view.findViewById(R.id.shoeNameTextView)
                    val shoeSizeTextView: TextView = view.findViewById(R.id.shoeSizeTextView)
                    val shoeCompanyTextView: TextView = view.findViewById(R.id.companyTextView)
                    val shoeDescriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)

                    if (addShoe != null) {
                        shoeNameTextView.text = addShoe.name
                        shoeSizeTextView.text = addShoe.size.toString()
                        shoeCompanyTextView.text = addShoe.company
                        shoeDescriptionTextView.text = addShoe.description

                        itemLayout.addView(view)
                    }

                }
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
}