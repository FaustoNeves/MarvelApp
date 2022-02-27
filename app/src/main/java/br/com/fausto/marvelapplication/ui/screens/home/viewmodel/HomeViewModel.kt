package br.com.fausto.marvelapplication.ui.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fausto.marvelapplication.data.dtos.MarvelCharacterDTO
import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.repository.IMarvelRepository
import br.com.fausto.marvelapplication.ui.constants.ApiConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IMarvelRepository
) :
    ViewModel() {

    val fetchCharactersStatus: MutableLiveData<Boolean> = MutableLiveData()
    val charactersDTOList = mutableListOf<MarvelCharacterDTO>()
    val errorMessage = MutableLiveData<String>()

    fun fetchCharacters(searchText: String) {
        viewModelScope.launch {
            when (val response = repository.fetchCharacters(searchText)) {
                is NetworkResponse.Success -> {
                    charactersDTOList.clear()
                    response.body.data!!.results!!.forEach {
                        if (!it.thumbnail!!.path!!.contains(ApiConstants.IMAGE_NOT_AVAILABLE)) {
                            charactersDTOList.add(
                                MarvelCharacterDTO(
                                    it.id!!,
                                    it.description!!.ifEmpty { ApiConstants.NO_DESCRIPTION_AVAILABLE },
                                    it.name!!,
                                    it.thumbnail!!.path!!,
                                    it.urls!![0].url!!
                                )
                            )
                        }
                    }
                    fetchCharactersStatus.value = true
                    if (response.body.data!!.count == 0) {
                        errorMessage.postValue(ApiConstants.NO_RESULTS_FOUND)
                    }
                }
                is NetworkResponse.ApiError -> {
                    errorMessage.postValue(ApiConstants.EMPTY_SEARCH_FIELD)
                }
                is NetworkResponse.NetworkError -> {
                    errorMessage.postValue(ApiConstants.CONNECTION_TIMEOUT)
                }
                is NetworkResponse.UnknownError -> {
                    errorMessage.postValue(ApiConstants.UNAUTHORIZED_REQUEST)
                }
            }
        }
    }
}