package com.example.movies.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movies.data.remote.FilmsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val filmsRepositoryImpl: FilmsRepositoryImpl,
//    private val state: SavedStateHandle
) :
    ViewModel() {
//    private val filmId = state.get<Int>("filmId")

//    private var _detailState = MutableStateFlow(DetailsState())
//    val detailsState = _detailState.asStateFlow()

//    init {
//        getFilms(filmId ?: -1)
//    }

//    private fun getFilms(id: Int) {
//        viewModelScope.launch {
//            _detailState.update {
//                it.copy(isLoading = true)
//            }
//            filmsRepositoryImpl.getLocalFilm(id).collectLatest { result ->
//                when (result) {
//                    is CheckConnection.Error<*> -> {
//                        _detailState.update {
//                            it.copy(isLoading = false)
//                        }
//                    }
//
//                    is CheckConnection.Success<*> -> {
//                        result.data.let { film ->
//                            _detailState.update {
//                                it.copy(film = film)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}