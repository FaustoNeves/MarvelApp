package br.com.fausto.marvelapplication.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.ui.screens.constants.BundleConstants

class CategorySelectionFragment : Fragment() {
    private var characterId: Int? = null
    private var imagePath: String? = null
    private var characterName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getInt(BundleConstants.CHARACTER_ID)
            imagePath = it.getString(BundleConstants.IMAGE_PATH)
            characterName = it.getString(BundleConstants.CHARACTER_NAME)
        }
        Toast.makeText(context, "$characterId, $imagePath", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}