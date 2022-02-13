package br.com.fausto.marvelapplication.ui.screens.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fausto.marvelapplication.data.dtos.MarvelCharacterDTO
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

    val fetchCharactersStatus: MutableLiveData<Boolean> = MutableLiveData()
    val marvelCharactersDTOList = mutableListOf<MarvelCharacterDTO>()
    val errorMessage = MutableLiveData<String>()

    fun fetchCharacters(searchText: String) {
        viewModelScope.launch {
            when (val response = repository.fetchCharacters(searchText)) {
                is NetworkResponse.Success -> {
                    marvelCharactersDTOList.clear()
                    response.body.data!!.results!!.forEach {
                        if (!it.thumbnail!!.path!!.contains("image_not_available")) {
                            marvelCharactersDTOList.add(
                                MarvelCharacterDTO(
                                    it.id!!,
                                    it.description!!.ifEmpty { "No description available" },
                                    it.name!!,
                                    it.thumbnail!!.path!!,
                                    it.urls!![0].url!!
                                )
                            )
                        }
                    }
                    fetchCharactersStatus.value = true
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