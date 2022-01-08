package br.com.fausto.marvelapplication.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.MarvelHeroDTO
import br.com.fausto.marvelapplication.ui.adapter.MarvelHeroesAdapter
import br.com.fausto.marvelapplication.ui.utils.NetworkStateHelper
import br.com.fausto.marvelapplication.ui.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    //    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainScreenViewModel by viewModels()

        val button = findViewById<Button>(R.id.button)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        var isConnected = false

        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        recyclerView = findViewById(R.id.all_marvel_heroes_rc)
        //Checks internet connection
        NetworkStateHelper(this).connectionState.observe(this, { hasConnection ->
            isConnected = if (hasConnection) {
                hasConnection
            } else {
                false
            }
        })

        button.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.Main) {
                viewModel.fetchHeroesStatus.observe(this@MainActivity, {
                    progressBar.visibility = View.INVISIBLE
                    setupRecyclerview(viewModel.marvelHeroesDTOList)
                })
                if (isConnected) {
                    viewModel.fetchHeroes(editText.text.toString())
                }
            }
        }

        viewModel.requestError.observe(this@MainActivity, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupRecyclerview(marvelHeroesDTOList: MutableList<MarvelHeroDTO>) {
        recyclerView.adapter = MarvelHeroesAdapter(marvelHeroesDTOList, this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}