package br.com.fausto.marvelapplication.ui.screens.home.fragment

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
import br.com.fausto.marvelapplication.data.dtos.MarvelCharacterDTO
import br.com.fausto.marvelapplication.ui.constants.BundleConstants
import br.com.fausto.marvelapplication.ui.constants.GeneralConstants
import br.com.fausto.marvelapplication.ui.constants.NavigationConstants
import br.com.fausto.marvelapplication.ui.screens.categoryselection.fragment.CategorySelectionFragment
import br.com.fausto.marvelapplication.ui.screens.home.adapter.HomeAdapter
import br.com.fausto.marvelapplication.ui.screens.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupSearchListener()
        setupOfficialWebsiteNavigation()
    }

    private fun setupObservers() {
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.fetchCharactersStatus.observe(viewLifecycleOwner) {
                setupRecyclerviewContent(viewModel.marvelCharactersDTOList)
                progress_bar1.visibility = View.INVISIBLE
                error_message.visibility = View.INVISIBLE
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            progress_bar1.visibility = View.INVISIBLE
            error_message.visibility = View.VISIBLE
            error_message.text = it
        }
        viewModel.fetchCharacters(GeneralConstants.INITIAL_QUERY_PARAMETER_SEARCH)
    }

    private fun setupRecyclerviewContent(charactersDTOList: MutableList<MarvelCharacterDTO>) {
        marvel_characters_rv.layoutManager = GridLayoutManager(context, 2)
        val charactersAdapter =
            HomeAdapter(
                charactersDTOList,
                requireContext()
            ) { characterId, imagePath, characterName, characterDescription, urlDetail ->
                setupCategoriesScreenNavigation(
                    characterId,
                    imagePath,
                    characterName,
                    characterDescription,
                    urlDetail
                )
            }
        marvel_characters_rv.adapter = charactersAdapter
    }

    private fun setupSearchListener() {
        search_text_input_edit_text.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                viewModel.fetchCharacters(it.toString())
                progress_bar1.visibility = View.VISIBLE
            }
        }
    }

    private fun setupCategoriesScreenNavigation(
        characterId: Int,
        imagePath: String,
        characterName: String,
        characterDescription: String,
        urlDetail: String
    ) {
        val categorySelectionFragment = CategorySelectionFragment()
        categorySelectionFragment.arguments = Bundle().apply {
            putInt(BundleConstants.CHARACTER_ID, characterId)
            putString(BundleConstants.IMAGE_PATH, imagePath)
            putString(BundleConstants.CHARACTER_NAME, characterName)
            putString(BundleConstants.CHARACTER_DESCRIPTION, characterDescription)
            putString(BundleConstants.URL_DETAIL, urlDetail)
        }
        parentFragmentManager.beginTransaction()
            .addToBackStack(NavigationConstants.CATEGORY_SELECTION_FRAGMENT)
            .replace(R.id.fragment_container_view, categorySelectionFragment)
            .commit()
    }

    private fun setupOfficialWebsiteNavigation() {
        marvel_website.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW).setData(
                    Uri.parse(GeneralConstants.MARVEL_OFFICIAL_WEBSITE_CHARACTERS)
                )
            )
        }
    }
}