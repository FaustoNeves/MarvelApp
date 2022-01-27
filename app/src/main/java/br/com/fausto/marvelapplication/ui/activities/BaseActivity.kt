package br.com.fausto.marvelapplication.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fausto.marvelapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initMarvelHeroesFragment()
    }

    private fun initMarvelHeroesFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, MarvelHeroesFragment())
            .addToBackStack("marvel_heroes_fragment")
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