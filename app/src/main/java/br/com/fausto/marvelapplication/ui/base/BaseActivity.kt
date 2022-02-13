package br.com.fausto.marvelapplication.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.ui.constants.NavigationConstants
import br.com.fausto.marvelapplication.ui.screens.home.fragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initMarvelCharactersFragment()
    }

    private fun initMarvelCharactersFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, HomeFragment())
            .addToBackStack(NavigationConstants.MARVEL_CHARACTERS_FRAGMENT)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            supportFragmentManager.popBackStack()
        else
            if (supportFragmentManager.backStackEntryCount == 1)
                finish()
    }
}