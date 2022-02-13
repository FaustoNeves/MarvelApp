package br.com.fausto.marvelapplication.ui.screens.comics.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.ui.constants.BundleConstants


class ComicsScreenFragment : Fragment() {
    private var characterId: Int? = null

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
        return inflater.inflate(R.layout.fragment_comics_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Toast.makeText(context, characterId.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(characterId: Int) =
            ComicsScreenFragment().apply {
                arguments = Bundle().apply {
                    putInt(BundleConstants.CHARACTER_ID, characterId)
                }
            }
    }
}