package br.com.fausto.marvelapplication.ui.screens.home.viewmodel

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
class HomeViewModel @Inject constructor(
    private val repository: IMarvelRepository
) :
    ViewModel() {

    val fetchHeroesStatus: MutableLiveData<Boolean> = MutableLiveData()
    val marvelHeroesDTOList = mutableListOf<MarvelHeroDTO>()
    val errorMessage = MutableLiveData<String>()

    fun fetchHeroes(searchText: String) {
        viewModelScope.launch {
            when (val response = repository.fetchHeroes(searchText)) {
                is NetworkResponse.Success -> {
                    marvelHeroesDTOList.clear()
                    response.body.data!!.results!!.forEach {
                        if (!it.thumbnail!!.path!!.contains("image_not_available")) {
                            marvelHeroesDTOList.add(
                                MarvelHeroDTO(
                                    it.id!!,
                                    it.description!!.ifEmpty { "No description available" },
                                    it.name!!,
                                    it.thumbnail!!.path!!,
                                    it.urls!![0].url!!
                                )
                            )
                        }
                    }
                    fetchHeroesStatus.value = true
                    if (response.body.data!!.count == 0) {
                        errorMessage.postValue("No results found for $searchText")
                    }
                }
                is NetworkResponse.ApiError -> {
                    errorMessage.postValue("Empty search field")
                }
                is NetworkResponse.NetworkError -> {
                    errorMessage.postValue("Check your internet connection")
                }
                is NetworkResponse.UnknownError -> {
                    errorMessage.postValue("Unauthorized request")
                }
            }
        }
    }
}