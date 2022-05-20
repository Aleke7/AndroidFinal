package com.example.practiceTask.CountryDetail

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.practiceTask.api.CountryItem
import com.example.practiceTask.api.CountryRepository
import kotlinx.coroutines.launch

class CountryDetailViewModel @ViewModelInject constructor(
    private val repository: CountryRepository,
    @Assisted private val state: SavedStateHandle
): ViewModel() {

    private val _response = MutableLiveData<CountryItem>()
    val responseCountry: LiveData<CountryItem>get() = _response

    private val slug = state.get<String>("Slug")

    init {
        getCountry()
    }

    private fun getCountry() = viewModelScope.launch {
        if(slug != null){
            repository.getCountry(slug).let { response ->
                if(response.isSuccessful){
                    _response.postValue(response.body())
                }
                else{
                    Log.d("UnSuccess", response.code().toString())
                }
            }
        }
    }

}