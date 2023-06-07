package com.example.danp_lab04.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="countryId")
    val countryId: Int,
    @ColumnInfo(name="name_en")
    val name_en: String,
    @ColumnInfo(name="name_es")
    val name_es: String,
    @ColumnInfo(name="continent_en")
    val continent_en: String,
    @ColumnInfo(name="continent_es")
    val continent_es: String,
    @ColumnInfo(name="capital_en")
    val capital_en: String,
    @ColumnInfo(name="capital_es")
    val capital_es: String,
    @ColumnInfo(name="dial_code")
    val dial_code: String,
    @ColumnInfo(name="code_2")
    val code_2: String,
    @ColumnInfo(name="code_3")
    val code_3: String,
    @ColumnInfo(name="tld")
    val tld: String,
    @ColumnInfo(name="km2")
    val km2: Int

)