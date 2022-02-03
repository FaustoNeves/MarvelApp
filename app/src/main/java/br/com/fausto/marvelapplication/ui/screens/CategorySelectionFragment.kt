package br.com.fausto.marvelapplication.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.ui.screens.constants.BundleConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_category_selection.*

class CategorySelectionFragment : Fragment() {
    private var characterId: Int? = null
    private var imagePath: String? = null
    private var characterName: String? = null
    private var characterDescription: String? = null
    private var urlDetail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getInt(BundleConstants.CHARACTER_ID)
            imagePath = it.getString(BundleConstants.IMAGE_PATH)
            characterName = it.getString(BundleConstants.CHARACTER_NAME)
            characterDescription = it.getString(BundleConstants.CHARACTER_DESCRIPTION)
            urlDetail = it.getString(BundleConstants.URL_DETAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        character_name.text = characterName
        Picasso.get().load(imagePath).into(default_character_image)
        character_description.text = characterDescription
    }
}