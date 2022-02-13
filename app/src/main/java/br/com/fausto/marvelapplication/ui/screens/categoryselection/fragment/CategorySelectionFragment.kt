package br.com.fausto.marvelapplication.ui.screens.categoryselection.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.ui.constants.BundleConstants
import br.com.fausto.marvelapplication.ui.constants.NavigationConstants
import br.com.fausto.marvelapplication.ui.screens.comics.fragment.ComicsScreenFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_category_selection.*

class CategorySelectionFragment : Fragment(), View.OnClickListener {
    private var characterId: Int? = null
    private var imagePath: String? = null
    private var characterName: String? = null
    private var characterDescription: String? = null
    private var urlDetail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrieveArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupScreenContent()
        setupClickListeners()
    }

    private fun retrieveArguments() {
        arguments?.let {
            characterId = it.getInt(BundleConstants.CHARACTER_ID)
            imagePath = it.getString(BundleConstants.IMAGE_PATH)
            characterName = it.getString(BundleConstants.CHARACTER_NAME)
            characterDescription = it.getString(BundleConstants.CHARACTER_DESCRIPTION)
            urlDetail = it.getString(BundleConstants.URL_DETAIL)
        }
    }

    private fun setupScreenContent() {
        comics_btn.setOnClickListener(this)
        character_name.text = characterName
        Picasso.get().load(imagePath).into(default_character_image)
        character_description.text = characterDescription
    }

    private fun setupClickListeners() {
        main_constraint_layout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            comics_btn -> {
                parentFragmentManager.beginTransaction()
                    .addToBackStack(NavigationConstants.COMICS_SCREEN_FRAGMENT)
                    .replace(
                        R.id.fragment_container_view, ComicsScreenFragment.newInstance(
                            characterId!!
                        )
                    ).commit()
            }
        }
    }
}