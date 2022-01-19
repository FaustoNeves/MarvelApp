package br.com.fausto.marvelapplication.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.MarvelHeroDTO
import br.com.fausto.marvelapplication.ui.adapter.MarvelHeroesAdapter
import br.com.fausto.marvelapplication.ui.viewmodels.MainScreenViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var textInput: TextInputEditText
    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        recyclerView = findViewById(R.id.all_marvel_heroes_rc)

        textInput = findViewById(R.id.search_text_input_edit_text)

        initialSetup()
        searchMarvelHero()
    }


    private fun initialSetup() {
        manageBarProgress(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        GlobalScope.launch(Dispatchers.Main) {
            viewModel.fetchHeroesStatus.observe(this@MainActivity, {
                manageBarProgress(false)
                setupRecyclerviewContent(viewModel.marvelHeroesDTOList)
            })
        }

        viewModel.errorMessage.observe(this@MainActivity, {
            manageBarProgress(false)
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.fetchHeroes("do")
    }

    private fun setupRecyclerviewContent(marvelHeroesDTOList: MutableList<MarvelHeroDTO>) {
        val marvelHeroesAdapter = MarvelHeroesAdapter(marvelHeroesDTOList, this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = marvelHeroesAdapter
        marvelHeroesAdapter.notifyDataSetChanged()
    }

    private fun searchMarvelHero() {
        textInput.addTextChangedListener {
            if (it.toString().isNotEmpty())
                viewModel.fetchHeroes(it.toString())
        }
    }

    private fun manageBarProgress(isActive: Boolean) {
        if (isActive) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.INVISIBLE
    }
}