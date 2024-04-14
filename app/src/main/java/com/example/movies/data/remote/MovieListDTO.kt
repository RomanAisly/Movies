package com.example.movies.data.remote

import com.example.movies.domain.FilmsData
import com.google.gson.annotations.SerializedName

data class MovieListDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ResultDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun MovieListDTO.getFilmsData(): FilmsData {
    return FilmsData(
        page = page,
        results = results,
        totalPages = totalPages,
        totalResults = totalResults
    )
}