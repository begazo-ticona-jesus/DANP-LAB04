package com.example.danp_lab04

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.danp_lab04.entities.CountryEntity

class CountryPagingSource(private val countryRepository: CountryRepository) : PagingSource<Int, CountryEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CountryEntity> {
        try {
            // Obtener el número de página actual desde los parámetros
            val currentPage = params.key ?: 0

            // Obtener los datos paginados del repositorio
            val countries = countryRepository.getCountries(currentPage)

            // Calcular las claves adyacentes para la paginación
            val prevKey = if (currentPage > 0) currentPage - 1 else null
            val nextKey = if (countries.isNotEmpty()) currentPage + 1 else null

            // Devolver los datos paginados y las claves adyacentes
            return LoadResult.Page(
                data = countries,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            // Manejar cualquier error durante la carga de datos
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CountryEntity>): Int? {
        TODO("Not yet implemented")
    }

}