package br.com.fausto.marvelapplication.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.MarvelHeroDTO
import br.com.fausto.marvelapplication.ui.adapter.MarvelHeroesAdapter
import br.com.fausto.marvelapplication.ui.viewmodels.MarvelHeroesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_marvel_heroes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MarvelHeroesFragment : Fragment() {
    private val viewModel: MarvelHeroesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_marvel_heroes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupSearchListener()
    }

    private fun setupObservers() {

        GlobalScope.launch(Dispatchers.Main) {
            viewModel.fetchHeroesStatus.observe(viewLifecycleOwner) {
                setupRecyclerviewContent(viewModel.marvelHeroesDTOList)
                progress_bar1.visibility = View.INVISIBLE
                error_message.visibility = View.INVISIBLE
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            progress_bar1.visibility = View.INVISIBLE
            error_message.visibility = View.VISIBLE
            error_message.text = it + search_text_input_edit_text.text.toString()
        }
        viewModel.fetchHeroes("do")
    }

    private fun setupRecyclerviewContent(marvelHeroesDTOList: MutableList<MarvelHeroDTO>) {
        marvel_heroes_rv.layoutManager = GridLayoutManager(context, 2)
        val marvelHeroesAdapter = MarvelHeroesAdapter(marvelHeroesDTOList, requireContext()) {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }
        marvel_heroes_rv.adapter = marvelHeroesAdapter
    }

    private fun setupSearchListener() {
        search_text_input_edit_text.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                viewModel.fetchHeroes(it.toString())
                progress_bar1.visibility = View.VISIBLE
            }
        }
    }
}