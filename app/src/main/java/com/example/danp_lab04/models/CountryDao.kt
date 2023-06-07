package com.example.danp_lab04.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.danp_lab04.entities.CountryEntity

@Dao
interface CountryDao{
    @Query("SELECT * FROM Country")
    fun getAllCountries(): List<CountryEntity>

    @Insert
    fun  insert(country: CountryEntity)

    @Insert
    fun  insert(countryEntity: List<CountryEntity>)
}