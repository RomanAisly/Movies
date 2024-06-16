package com.example.movies.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.remote.CheckConnection
import com.example.movies.data.remote.FilmsRepository
import com.example.movies.ui.screens.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository,
    private val state: SavedStateHandle
) :
    ViewModel() {
    private val filmId = state.get<Int>("filmId")

    private var _detailState = MutableStateFlow(DetailsState())
    val detailsState = _detailState.asStateFlow()

    init {
        getFilms(filmId ?: -1)
    }

    private fun getFilms(id: Int) {
        viewModelScope.launch {
            _detailState.update {
                it.copy(isLoading = true)
            }
            filmsRepository.getLocalFilm(id).collectLatest { result ->
                when (result) {
                    is CheckConnection.Error -> {
                        _detailState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is CheckConnection.Loading -> {
                        _detailState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is CheckConnection.Success -> {
                        result.data.let { film ->
                            _detailState.update {
                                it.copy(film = film)
                            }
                        }
                    }
                }
            }
        }
    }
}