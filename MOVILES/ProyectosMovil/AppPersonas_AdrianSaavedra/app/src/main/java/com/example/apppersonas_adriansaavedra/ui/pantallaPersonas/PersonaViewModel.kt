package com.example.apppersonas_adriansaavedra.ui.pantallaPersonas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apppersonas_adriansaavedra.R
import com.example.apppersonas_adriansaavedra.domain.modelo.Persona
import com.example.apppersonas_adriansaavedra.domain.usecases.personas.*
import com.example.apppersonas_adriansaavedra.ui.common.StringProvider
import com.example.apppersonas_adriansaavedra.ui.common.UiEvent


class PersonaViewModel(
    private val stringProvider: StringProvider,
    private val addPersonUseCase: AddPersonUseCase,
    private val getPersonsUseCase: GetPersonsUseCase,
    private val deletePersonUseCase: DeletePersonUseCase,
    private val updatePersonUseCase: UpdatePersonUseCase,
    private val getPersonId: GetPersonUseCase,
) : ViewModel() {
    private var indice = 0
    private val _uiState = MutableLiveData<PersonaState>()
    val uiState: LiveData<PersonaState> get() = _uiState

    init {
        val personas = getPersonsUseCase.invoke()
        val personaInicial = personas.firstOrNull() ?: Persona()
        indice = personaInicial.id
        _uiState.value = PersonaState(
            persona = personaInicial,
            aviso = null,
            anterior = false,
            siguiente = personas.size > 1,
            indiceGrafica = 1,
            longitudLista = personas.size
        )
    }

    private fun updateIndiceLongitud() {
        val personas = getPersonsUseCase.invoke()
        val currentIndex = personas.indexOfFirst { it.id == indice }
        _uiState.value = _uiState.value?.copy(
            indiceGrafica = currentIndex+1,
            longitudLista = personas.size
        )
    }

    fun addPersona(personaArg: Persona) {
        if (personaArg.nombre.isEmpty()) {
            _uiState.value = _uiState.value?.copy(aviso = UiEvent.ShowSnackbar(stringProvider.getString(R.string.rellenanombre)))
        } else {
            if (!addPersonUseCase(personaArg)) {
                _uiState.value = _uiState.value?.copy(aviso =  UiEvent.ShowSnackbar(stringProvider.getString(R.string.erroragregar)))
            } else {
                _uiState.value = _uiState.value?.copy(
                    persona = personaArg,
                    aviso = UiEvent.ShowSnackbar(stringProvider.getString(R.string.personaagregada)),
                    anterior = getPersonsUseCase.invoke().size > 1,
                    siguiente = false,
                    updatedelete = true
                )
                indice = personaArg.id
                _uiState.value = _uiState.value?.copy(aviso = UiEvent.PopBackStack)
            }
        }
        updateIndiceLongitud()
    }

    fun deletePersona() {
        val currentPersona = _uiState.value?.persona
        if (currentPersona != null && deletePersonUseCase(currentPersona)) {
            val personas = getPersonsUseCase.invoke()
            if (personas.isEmpty()) {
                _uiState.value = PersonaState(
                    persona = Persona(),
                    aviso = UiEvent.ShowSnackbar(stringProvider.getString(R.string.personaeliminada)),
                    anterior = false,
                    siguiente = false,
                    updatedelete = false,
                    longitudLista = 0
                )
            } else {
                val newPersona = if (currentPersona.id < personas.last().id) {
                    personas.find { it.id > currentPersona.id } ?: personas.first()
                } else {
                    personas.last()
                }
                _uiState.value = PersonaState(
                    persona = newPersona,
                    aviso = UiEvent.ShowSnackbar(stringProvider.getString(R.string.personaeliminada)),
                    anterior = newPersona.id > personas.first().id,
                    siguiente = newPersona.id < personas.last().id,
                    updatedelete = true,
                    longitudLista = personas.size
                )
                indice = newPersona.id
            }
            _uiState.value = _uiState.value?.copy(aviso = UiEvent.PopBackStack)
        } else {
            _uiState.value = _uiState.value?.copy(
                aviso = UiEvent.ShowSnackbar(stringProvider.getString(R.string.agregaprimero)),
                anterior = false,
                siguiente = false,
                updatedelete = false
            )
        }
        updateIndiceLongitud()
    }

    fun updatePersona(newPersona: Persona) {
        if (newPersona.nombre.isEmpty()) {
            _uiState.value = _uiState.value?.copy(aviso = UiEvent.ShowSnackbar(stringProvider.getString(R.string.rellenanombre)))
        } else {
            val currentPersona = _uiState.value?.persona
            if (currentPersona != null) {
                updatePersonUseCase.invoke(currentPersona, newPersona)
                _uiState.value = _uiState.value?.copy(
                    persona = newPersona,
                    aviso = UiEvent.ShowSnackbar(stringProvider.getString(R.string.personaactualizada))
                )
                indice = newPersona.id
            }
        }
        updateIndiceLongitud()
    }

    fun getPersona(id: Int) {
        val personas = getPersonsUseCase.invoke()
        val persona = getPersonId(id)
        indice = id
        val currentIndex = personas.indexOfFirst { it.id == id }
        _uiState.value = PersonaState(
            persona = persona,
            aviso = null,
            anterior = currentIndex > 0,
            siguiente = currentIndex < personas.size - 1,
            indiceGrafica = currentIndex + 1,
            longitudLista = personas.size,
            updatedelete = true
        )
    }

    fun getSiguientePersona() {
        val personas = getPersonsUseCase.invoke()
        val currentIndex = personas.indexOfFirst { it.id == indice }
        if (currentIndex < personas.size - 1) {
            val nextPersona = personas[currentIndex + 1]
            getPersona(nextPersona.id)
        }
    }

    fun getAnteriorPersona() {
        val personas = getPersonsUseCase.invoke()
        val currentIndex = personas.indexOfFirst { it.id == indice }
        if (currentIndex > 0) {
            val previousPersona = personas[currentIndex - 1]
            getPersona(previousPersona.id)
        }
    }

    fun avisoMostrado() {
        _uiState.value = _uiState.value?.copy(aviso = null)
    }
}

class PersonaViewModelFactory(
    private val stringProvider: StringProvider,
    private val addPersonUseCase: AddPersonUseCase,
    private val getPersonsUseCase: GetPersonsUseCase,
    private val deletePersonUseCase: DeletePersonUseCase,
    private val updatePersonUseCase: UpdatePersonUseCase,
    private val getPersonId: GetPersonUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonaViewModel(
                stringProvider,
                addPersonUseCase,
                getPersonsUseCase,
                deletePersonUseCase,
                updatePersonUseCase,
                getPersonId
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}