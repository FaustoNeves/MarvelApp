package br.com.fausto.marvelapplication.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.MarvelHeroDTO
import br.com.fausto.marvelapplication.ui.adapter.MarvelHeroesAdapter
import br.com.fausto.marvelapplication.ui.screens.constants.BundleConstants
import br.com.fausto.marvelapplication.ui.screens.constants.GeneralConstants
import br.com.fausto.marvelapplication.ui.screens.constants.NavigationConstants
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
        setupOfficialWebsiteNavigation()
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
        viewModel.fetchHeroes(GeneralConstants.INITIAL_QUERY_PARAMETER_SEARCH)
    }

    private fun setupRecyclerviewContent(marvelHeroesDTOList: MutableList<MarvelHeroDTO>) {
        marvel_heroes_rv.layoutManager = GridLayoutManager(context, 2)
        val marvelHeroesAdapter =
            MarvelHeroesAdapter(
                marvelHeroesDTOList,
                requireContext()
            ) { characterId, imagePath, characterName ->
                setupCategoriesScreenNavigation(characterId, imagePath, characterName)
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

    private fun setupCategoriesScreenNavigation(
        characterId: Int,
        imagePath: String,
        characterName: String
    ) {
        val arguments = Bundle()
        arguments.putInt(BundleConstants.CHARACTER_ID, characterId)
        arguments.putString(BundleConstants.IMAGE_PATH, imagePath)
        arguments.putString(BundleConstants.CHARACTER_NAME, characterName)
        val categorySelectionFragment = CategorySelectionFragment()
        categorySelectionFragment.arguments = arguments
        parentFragmentManager.beginTransaction()
            .addToBackStack(NavigationConstants.CATEGORY_SELECTION_FRAGMENT)
            .replace(R.id.fragment_container_view, categorySelectionFragment)
            .commit()
    }

    private fun setupOfficialWebsiteNavigation() {
        marvel_website.setOnClickListener() {
            startActivity(
                Intent(Intent.ACTION_VIEW).setData(
                    Uri.parse(GeneralConstants.MARVEL_OFFICIAL_WEBSITE_CHARACTERS)
                )
            )
        }
    }
}