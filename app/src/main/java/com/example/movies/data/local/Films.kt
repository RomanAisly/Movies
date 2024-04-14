package com.example.movies.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Films(
    val title: String,
    val poster: String,
    val description: String,
    var rating: Double = 0.0,
    var isInFavorites: Boolean = false
) : Parcelable
