package com.example.practiceTask.Countries

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceTask.api.CountryItem
import com.example.practiceTask.api.CountryRepository
import kotlinx.coroutines.launch

class CountriesViewModel @ViewModelInject constructor(
    private val repository: CountryRepository
): ViewModel() {

    private val _response = MutableLiveData<List<CountryItem>>()
    val responseCountry: LiveData<List<CountryItem>>get() = _response

    init {
        getCountries()
    }

    private fun getCountries() = viewModelScope.launch {
        repository.getCountries().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("UnSuccessful", response.code().toString())
            }
        }
    }

}