package com.example.practiceTask.CountryDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.practiceTask.R
import com.example.practiceTask.databinding.FragmentCountryDetailBinding

class CountryDetailFragment : Fragment(R.layout.fragment_country_detail) {

    private val viewModel: CountryDetailViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCountryDetailBinding.bind(view)

        viewModel.responseCountry.observe(viewLifecycleOwner){ country ->
            binding.apply {
                countryNameDetail.text = country.Country
                countrySlugDetail.text = country.Slug
                countryIsoDetail.text = country.ISO2
            }

        }

    }

}