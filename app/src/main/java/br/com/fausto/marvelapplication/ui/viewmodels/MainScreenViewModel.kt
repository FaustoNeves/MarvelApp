package br.com.fausto.marvelapplication.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fausto.marvelapplication.data.dtos.MarvelHeroDTO
import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.repository.IMarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: IMarvelRepository
) :
    ViewModel() {

    val fetchHeroesStatus = MutableLiveData(false)
    val marvelHeroesDTOList = mutableListOf<MarvelHeroDTO>()
    val requestError = MutableLiveData<String>()

    fun fetchHeroes(firstChar: String) {
        viewModelScope.launch {
            when (val response = repository.fetchHeroes(firstChar)) {
                is NetworkResponse.Success -> {
                    marvelHeroesDTOList.clear()
                    response.body.data!!.results!!.forEach {
                        marvelHeroesDTOList.add(
                            MarvelHeroDTO(
                                it.id!!,
                                it.description!!,
                                it.name!!,
                                it.thumbnail!!.path!!
                            )
                        )
                    }
                    fetchHeroesStatus.value = true
                }
                is NetworkResponse.ApiError -> {
                    requestError.postValue("Server down. Try again later")
                }
                is NetworkResponse.NoResultsError -> {
                    requestError.postValue("No results found")
                }
                is NetworkResponse.NetworkError -> {
                    requestError.postValue("Request failed")
                }
                is NetworkResponse.UnknownError -> {
                    requestError.postValue("Unknown error")
                }
            }
        }
    }
}