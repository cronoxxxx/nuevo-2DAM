package com.example.apppersonas_adriansaavedra.ui.pantallaMain

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apppersonas_adriansaavedra.R
import com.example.apppersonas_adriansaavedra.databinding.ActivityMainBinding
import com.example.apppersonas_adriansaavedra.domain.usecases.personas.GetPersonsUseCase
import com.example.apppersonas_adriansaavedra.ui.Constantes
import com.example.apppersonas_adriansaavedra.ui.common.MarginItemDecoration
import com.example.apppersonas_adriansaavedra.ui.pantallaPersonas.PersonaActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonasAdapter

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(GetPersonsUseCase())
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPersonas()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        adapter = PersonasAdapter(::click)
        with(binding) {
            rvPersonas.adapter = adapter
            rvPersonas.layoutManager = GridLayoutManager(this@MainActivity, 1)
            rvPersonas.addItemDecoration(MarginItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.margin
                )
            ))
        }

        binding.fabAddPerson.setOnClickListener {
            agregarPersonaVacio()
        }
        observarViewModel()
    }

    private fun agregarPersonaVacio() {
        val intent = Intent(this, PersonaActivity::class.java)
        intent.putExtra(Constantes.MODO, Constantes.MODO_AGREGAR)
        startActivity(intent)
    }


    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state.aviso?.let { aviso ->
                Toast.makeText(this@MainActivity, aviso, Toast.LENGTH_SHORT).show()
                viewModel.avisoMostrado()
            }
            adapter.submitList(state.personas)
        }
    }

    private fun click(id: Int) {
        val intent = Intent(this, PersonaActivity::class.java)
        intent.putExtra(Constantes.ID, id)
        startActivity(intent)
    }
}