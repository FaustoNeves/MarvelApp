package br.com.fausto.marvelapplication.ui.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.ui.utils.Status
import br.com.fausto.marvelapplication.ui.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainScreenViewModel by viewModels()

        val button = findViewById<Button>(R.id.button)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        button.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                viewModel.marvelHeroesList.observe(this@MainActivity, {
                    if (it.status == Status.LOADING) {
                        Log.e("loading status", "abidjama")
                        progressBar.visibility = View.VISIBLE
                    }
                    if (it.status != Status.LOADING) {
                        Log.e("success status", "abidjama")
                        progressBar.visibility = View.INVISIBLE
                    }
                    Log.e("status", it.status.name)
                    Log.e("data", it.data.toString())
                })
                viewModel.fetchHeroes()
            }
        }
    }
}