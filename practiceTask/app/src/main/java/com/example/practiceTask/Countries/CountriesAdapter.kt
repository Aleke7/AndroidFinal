package com.example.practiceTask.Countries

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceTask.api.CountryItem
import com.example.practiceTask.databinding.FragmentCountriesBinding
import com.example.practiceTask.databinding.FragmentCountryBinding

class CountriesAdapter(private val listener: CountriesFragment):
    RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {


    inner class CountriesViewHolder(val binding: FragmentCountryBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val country = countries[position]
                        listener.onItemClick(country.Slug)
                    }
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Slug: String)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<CountryItem>() {
        override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem) =
            oldItem.Slug == newItem.Slug

        override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem) =
            oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var countries: List<CountryItem>get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        return CountriesViewHolder(FragmentCountryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
       val currentCountry = countries[position]

        holder.binding.apply {
            countryName.text = currentCountry.Country
            countrySlug.text = currentCountry.Slug
            countryIso.text = currentCountry.ISO2
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }


}