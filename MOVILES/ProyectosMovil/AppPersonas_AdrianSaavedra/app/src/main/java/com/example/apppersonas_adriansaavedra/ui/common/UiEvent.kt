package com.example.apppersonas_adriansaavedra.ui.common

sealed class UiEvent{

    data object PopBackStack: UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()

}