package br.com.fausto.marvelapplication.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fausto.marvelapplication.data.datasource.remote.responses.all_heroes.FetchAllMarvelHeroesResponse
import br.com.fausto.marvelapplication.data.dtos.MarvelHeroDTO
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

    val marvelHeroesList = MutableLiveData<Resource<FetchAllMarvelHeroesResponse>>()
    val marvelHeroesDTOList = mutableListOf<MarvelHeroDTO>()

    fun fetchHeroes(firstChar: String) {
        marvelHeroesList.value = Resource(Status.LOADING, null, null)
        try {
            Log.e("first time going", marvelHeroesList.value!!.status.toString())

        } catch (e: Exception) {

        }
        marvelHeroesDTOList.clear()
        viewModelScope.launch {
            val response = repository.fetchHeroes(firstChar)
            if (response.code == 200) {
                response.data!!.results!!.forEach {
                    marvelHeroesDTOList.add(
                        MarvelHeroDTO(
                            it.id!!,
                            it.description!!,
                            it.name!!,
                            it.thumbnail!!.path!!
                        )
                    )
                }
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