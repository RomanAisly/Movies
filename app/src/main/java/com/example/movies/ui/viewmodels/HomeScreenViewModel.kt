package com.example.movies.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.remote.CheckConnection
import com.example.movies.data.remote.FilmsRepository
import com.example.movies.data.remote.ResultDTO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeScreenViewModel(private val filmsRepository: FilmsRepository) : ViewModel() {
    private val _films = MutableStateFlow<List<ResultDTO>>(emptyList())
    val films = _films.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            filmsRepository.getFilmsByApi().collectLatest { result ->
                when (result) {
                    is CheckConnection.Fail -> {
                        _showErrorToastChannel.send(true)
                    }

                    is CheckConnection.Success -> {
                        result.data?.let { films ->
                            _films.update { films }
                        }
                    }
                }
            }
        }
    }
}