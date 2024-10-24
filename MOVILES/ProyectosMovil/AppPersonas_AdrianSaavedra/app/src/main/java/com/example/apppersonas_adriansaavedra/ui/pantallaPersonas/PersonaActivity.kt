package com.example.apppersonas_adriansaavedra.ui.pantallaPersonas

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.example.apppersonas_adriansaavedra.R
import com.example.apppersonas_adriansaavedra.databinding.ActivityPersonaBinding
import com.example.apppersonas_adriansaavedra.domain.modelo.Persona
import com.example.apppersonas_adriansaavedra.domain.usecases.personas.*
import com.example.apppersonas_adriansaavedra.ui.Constantes
import com.example.apppersonas_adriansaavedra.ui.common.StringProvider
import com.example.apppersonas_adriansaavedra.ui.common.UiEvent
import java.util.Calendar

class PersonaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonaBinding
    private var pulsarAgregar = false
    private val viewModel: PersonaViewModel by viewModels {
        PersonaViewModelFactory(
            StringProvider.instance(this),
            AddPersonUseCase(),
            GetPersonsUseCase(),
            DeletePersonUseCase(),
            UpdatePersonUseCase(),
            GetPersonUseCase()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonaBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        pulsarAgregar = intent.getStringExtra(Constantes.MODO) == Constantes.MODO_AGREGAR
        if (pulsarAgregar) {
            vaciarUI()
        } else {
            val id = intent.getIntExtra(Constantes.ID, Constantes.DEFAULT_VALUE_INT)
            if (id != Constantes.DEFAULT_VALUE_INT) {
                viewModel.getPersona(id)
            }
        }
        observarViewModel()
        eventos()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@PersonaActivity) { state ->
            state.aviso?.let { event ->
                when (event) {
                    is UiEvent.ShowSnackbar -> {
                        Toast.makeText(this@PersonaActivity, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is UiEvent.PopBackStack -> {
                        finish()
                    }
                }
                viewModel.avisoMostrado()
            }
            if(!pulsarAgregar) {
                pintarPersona(state)
                pintarInterfaz(state)
            }
        }
    }

    private fun vaciarUI() {
        with(binding) {
            anteriorButton.visibility = View.INVISIBLE
            siguienteButton.visibility = View.INVISIBLE
            updateButton.visibility = View.INVISIBLE
            deleteButton.visibility = View.INVISIBLE
            tvIndice.visibility = View.INVISIBLE
            etNombre.setText(getString(R.string.null_string))
            etEmail.setText(getString(R.string.null_string))
            slEstatura.value = getString(R.string.slider_min_value).toFloat()
            etClave.setText(getString(R.string.null_string))
            btnDatePicker.text = getString(R.string.first_date)
            tglGenero.clearChecked()
            cbTerms.isChecked = false
            addButton.visibility = View.VISIBLE
        }
    }

    private fun pintarInterfaz(state: PersonaState) {
        binding.tvIndice.text =
            getString(R.string.indice_format, state.indiceGrafica, state.longitudLista)
        binding.anteriorButton.visibility =
            if (state.anterior == true) View.VISIBLE else View.INVISIBLE
        binding.siguienteButton.visibility =
            if (state.siguiente == true) View.VISIBLE else View.INVISIBLE
        binding.updateButton.visibility =
            if (state.updatedelete == true) View.VISIBLE else View.INVISIBLE
        binding.deleteButton.visibility =
            if (state.updatedelete == true) View.VISIBLE else View.INVISIBLE
    }

    private fun pintarPersona(state: PersonaState) {
        with(binding) {
            etNombre.setText(state.persona.nombre)
            etEmail.setText(state.persona.email)
            slEstatura.value = state.persona.estatura.toFloat()
            etClave.setText(state.persona.clave)
            btnDatePicker.text = state.persona.fechaNacimiento
            when (state.persona.genero) {
                getString(R.string.hombre) -> tglGenero.check(R.id.btnHombre)
                getString(R.string.mujer) -> tglGenero.check(R.id.btnMujer)
                else -> tglGenero.clearChecked()
            }
            cbTerms.isChecked = state.persona.aceptarTerminos
        }
    }

    private fun eventos() {
        with(binding) {
            btnDatePicker.setOnClickListener {
                showDatePickerDialog()
            }

            addButton.setOnClickListener {
                val persona = crearPersonaDesdeUI()
                viewModel.addPersona(persona)
            }

            deleteButton.setOnClickListener {
                viewModel.deletePersona()
            }

            updateButton.setOnClickListener {
                val newPersona = crearPersonaDesdeUI()
                viewModel.updatePersona(newPersona)
            }

            siguienteButton.setOnClickListener {
                viewModel.getSiguientePersona()
            }

            anteriorButton.setOnClickListener {
                viewModel.getAnteriorPersona()
            }
        }
    }

    private fun crearPersonaDesdeUI(): Persona =
        Persona(
            nombre = binding.etNombre.text.toString(),
            email = binding.etEmail.text.toString(),
            estatura = binding.slEstatura.value.toInt(),
            clave = binding.etClave.text.toString(),
            fechaNacimiento = binding.btnDatePicker.text.toString(),
            genero = when (binding.tglGenero.checkedButtonId) {
                R.id.btnHombre -> getString(R.string.hombre)
                R.id.btnMujer -> getString(R.string.mujer)
                else -> getString(R.string.null_string)
            },
            aceptarTerminos = binding.cbTerms.isChecked
        )


    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = formatDateText(selectedDay, selectedMonth, selectedYear)
                binding.btnDatePicker.text = formattedDate
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun formatDateText(day: Int, month: Int, year: Int): String {
        return "$day/${month + 1}/$year"
    }
}