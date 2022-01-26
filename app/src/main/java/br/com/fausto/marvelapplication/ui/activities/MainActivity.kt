package br.com.fausto.marvelapplication.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import br.com.fausto.marvelapplication.data.dtos.MarvelHeroDTO
import br.com.fausto.marvelapplication.databinding.ActivityMainBinding
import br.com.fausto.marvelapplication.ui.adapter.MarvelHeroesAdapter
import br.com.fausto.marvelapplication.ui.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var screenBind: ActivityMainBinding
    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(screenBind.root)

        initialSetup()
        searchMarvelHero()
    }

    private fun initialSetup() {
        manageBarProgress(true)
        screenBind.marvelHeroesRv.layoutManager = GridLayoutManager(this, 2)

        GlobalScope.launch(Dispatchers.Main) {
            viewModel.fetchHeroesStatus.observe(this@MainActivity) {
                manageBarProgress(false)
                setupRecyclerviewContent(viewModel.marvelHeroesDTOList)
            }
        }

        viewModel.errorMessage.observe(this@MainActivity) {
            manageBarProgress(false)
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchHeroes("do")
    }

    private fun setupRecyclerviewContent(marvelHeroesDTOList: MutableList<MarvelHeroDTO>) {
        val marvelHeroesAdapter = MarvelHeroesAdapter(marvelHeroesDTOList, this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        screenBind.marvelHeroesRv.adapter = marvelHeroesAdapter
    }

    private fun searchMarvelHero() {
        screenBind.searchTextInputEditText.addTextChangedListener {
            if (it.toString().isNotEmpty())
                viewModel.fetchHeroes(it.toString())
        }
    }

    private fun manageBarProgress(isActive: Boolean) {
        if (isActive) screenBind.progressBar.visibility = View.VISIBLE
        else screenBind.progressBar.visibility = View.INVISIBLE
    }
}