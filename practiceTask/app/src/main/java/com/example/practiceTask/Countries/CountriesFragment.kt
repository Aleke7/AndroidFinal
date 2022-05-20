package com.example.practiceTask.Countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiceTask.R
import com.example.practiceTask.databinding.FragmentCountriesBinding


class CountriesFragment : Fragment(R.layout.fragment_countries), CountriesAdapter.OnItemClickListener {

    private val viewModel: CountriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCountriesBinding.bind(view)
        val countriesAdapter = CountriesAdapter(this)

        binding.apply {
            rvCountries.apply {
                adapter = countriesAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.responseCountry.observe(viewLifecycleOwner) { countries ->
            countriesAdapter.countries = countries
        }

    }

    override fun onItemClick(slug: String) {
        val action = CountriesFragmentDirections.actionCountriesFragmentToCountryDetailFragment(slug)
        findNavController().navigate(action)
    }

}