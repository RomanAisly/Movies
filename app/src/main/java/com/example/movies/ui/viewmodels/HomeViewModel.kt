package com.example.movies.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.local.FilmsEntity
import com.example.movies.data.remote.CheckConnection
import com.example.movies.data.remote.FilmsRepositoryImpl
import com.example.movies.data.remote.ResultDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val filmsRepositoryImpl: FilmsRepositoryImpl) :
    ViewModel() {

    private val _allFilms = MutableStateFlow<List<ResultDTO>>(emptyList())
    val allFilms = _allFilms.asStateFlow()

    private val _allLocalFilms = MutableStateFlow<List<FilmsEntity>>(emptyList())
    val allLocalFilms = _allLocalFilms.asStateFlow()

    private val _showErrorToast = Channel<Boolean>()
    val showErrorToast = _showErrorToast.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            filmsRepositoryImpl.getFilmsRemote().collectLatest { result ->
                when (result) {
                    is CheckConnection.Error -> {
                        _showErrorToast.send(true)
                    }

                    is CheckConnection.Success -> {
                        result.data?.let { films ->
                            _allFilms.update { films }

                        }
                    }
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            filmsRepositoryImpl.getFilmsLocal().collectLatest { result ->
                when (result) {
                    is CheckConnection.Error -> {
                        result.data?.let { films ->
                            _allLocalFilms.update { films }
                        }
                    }

                    is CheckConnection.Success -> {
                        _showErrorToast.send(false)
                    }
                }
            }
        }
    }
}