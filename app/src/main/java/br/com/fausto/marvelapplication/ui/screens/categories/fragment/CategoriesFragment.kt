package br.com.fausto.marvelapplication.ui.screens.categories.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.CategoryDTO
import br.com.fausto.marvelapplication.ui.constants.BundleConstants
import br.com.fausto.marvelapplication.ui.screens.categories.adapter.CategoriesAdapter
import br.com.fausto.marvelapplication.ui.screens.categories.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_categories.*

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private var characterId: Int? = null
    private var selectedCategory: String? = null
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getInt(BundleConstants.CHARACTER_ID)
            selectedCategory = it.getString(BundleConstants.SELECTED_CATEGORY)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(characterId: Int, selectedCategory: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putInt(BundleConstants.CHARACTER_ID, characterId)
                    putString(BundleConstants.SELECTED_CATEGORY, selectedCategory)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObservers()
        setupScreenContent()
    }

    private fun setupRecyclerViewContent(categoryDTOList: MutableList<CategoryDTO>) {
        categories_rv.layoutManager = GridLayoutManager(requireContext(), 3)
        categories_rv.adapter = CategoriesAdapter(categoryDTOList, requireContext())
    }

    private fun setupObservers() {
        viewModel.fetchCategoryStatus.observe(viewLifecycleOwner) {
            progress_bar2.visibility = View.INVISIBLE
            error_message.visibility = View.VISIBLE
            setupRecyclerViewContent(viewModel.categoriesListDTO)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            progress_bar2.visibility = View.INVISIBLE
            error_message.visibility = View.VISIBLE
            error_message.text = it
        }
    }

    private fun setupScreenContent() {
        viewModel.fetchContentById(characterId!!, selectedCategory!!)
        categories_header.text = selectedCategory
    }
}