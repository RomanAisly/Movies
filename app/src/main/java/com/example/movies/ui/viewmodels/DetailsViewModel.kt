package com.example.movies.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movies.data.remote.FilmsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val filmsRepositoryImpl: FilmsRepositoryImpl
) : ViewModel() {

}