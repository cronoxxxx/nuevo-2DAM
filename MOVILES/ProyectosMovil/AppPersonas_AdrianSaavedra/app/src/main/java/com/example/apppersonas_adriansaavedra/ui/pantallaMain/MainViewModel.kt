package com.example.apppersonas_adriansaavedra.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apppersonas_adriansaavedra.domain.usecases.personas.GetPersonsUseCase
class MainViewModel(private val getPersonsUseCase: GetPersonsUseCase) : ViewModel() {
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    init {
        _uiState.value = MainState(
            aviso = null
        )
    }
    fun getPersonas() {
        _uiState.value = _uiState.value?.copy(personas = getPersonsUseCase())
    }
    fun avisoMostrado() {
        _uiState.value = _uiState.value?.copy(aviso = null)
    }
}

class MainViewModelFactory(private val getPersonsUseCase: GetPersonsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(getPersonsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}