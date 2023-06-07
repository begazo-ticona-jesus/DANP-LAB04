package com.example.danp_lab04

import com.example.danp_lab04.entities.CountryEntity
import com.example.danp_lab04.models.AppDatabase
import com.example.danp_lab04.models.CountryDao

class CountryRepository(private val appDatabase: AppDatabase) {
     fun getCountries(page: Int): List<CountryEntity> {
        return appDatabase.countryDao().getAllCountries()
    }

    fun insertCountry(countryList: List<CountryEntity>){
        return appDatabase.countryDao().insert(countryList)
    }
}