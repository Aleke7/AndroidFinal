package com.example.practiceTask.api

import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCountries() = apiService.getCountries()
    suspend fun getCountry(Slug: String) = apiService.getCountry(Slug)
}