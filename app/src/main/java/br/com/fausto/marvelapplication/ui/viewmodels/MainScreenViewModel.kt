package br.com.fausto.marvelapplication.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fausto.marvelapplication.data.datasource.responses.hero.MarvelHero
import br.com.fausto.marvelapplication.data.repository.IMarvelRepository
import br.com.fausto.marvelapplication.ui.utils.Resource
import br.com.fausto.marvelapplication.ui.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: IMarvelRepository
) :
    ViewModel() {

    var marvelHeroesList = MutableLiveData<Resource<MarvelHero>>()

    fun fetchHeroes() {
        viewModelScope.launch {
            marvelHeroesList.postValue(Resource(Status.LOADING, null, null))
            val response = repository.fetchHeroes()
            if (response.code == 200) {
                marvelHeroesList.postValue(
                    Resource(
                        Status.SUCCESS,
                        response,
                        "Success"
                    )
                )
            } else {
                marvelHeroesList.postValue(Resource(Status.ERROR, null, "error"))
            }
        }
    }
}