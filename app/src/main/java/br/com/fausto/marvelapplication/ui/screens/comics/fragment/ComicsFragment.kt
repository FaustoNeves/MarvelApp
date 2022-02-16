package br.com.fausto.marvelapplication.ui.screens.comics.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.ComicsDTO
import br.com.fausto.marvelapplication.ui.constants.BundleConstants
import br.com.fausto.marvelapplication.ui.screens.comics.adapter.ComicsAdapter
import br.com.fausto.marvelapplication.ui.screens.comics.viewmodel.ComicsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_comics.*

@AndroidEntryPoint
class ComicsFragment : Fragment() {
    private var characterId: Int? = null
    private val viewModel: ComicsViewModel by viewModels()

    companion object {
        @JvmStatic
        fun newInstance(characterId: Int) =
            ComicsFragment().apply {
                arguments = Bundle().apply {
                    putInt(BundleConstants.CHARACTER_ID, characterId)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getInt(BundleConstants.CHARACTER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObservers()
    }

    private fun setupRecyclerViewContent(comicsDTOList: MutableList<ComicsDTO>) {
        comics_rv.layoutManager = GridLayoutManager(requireContext(), 3)
        comics_rv.adapter = ComicsAdapter(comicsDTOList, requireContext())
    }

    private fun setupObservers() {
        viewModel.fetchComicsStatus.observe(viewLifecycleOwner) {
            progress_bar2.visibility = View.INVISIBLE
            error_message.visibility = View.VISIBLE
            setupRecyclerViewContent(viewModel.comicsListDTO)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            progress_bar2.visibility = View.INVISIBLE
            error_message.visibility = View.VISIBLE
            error_message.text = it
        }
        viewModel.fetchComicsById(characterId!!)
    }
}