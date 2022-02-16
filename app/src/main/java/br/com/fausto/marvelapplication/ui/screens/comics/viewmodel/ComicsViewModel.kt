package br.com.fausto.marvelapplication.ui.screens.comics.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fausto.marvelapplication.data.dtos.ComicsDTO
import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.repository.IMarvelRepository
import br.com.fausto.marvelapplication.ui.constants.ApiConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val repository: IMarvelRepository
) : ViewModel() {

    val fetchComicsStatus = MutableLiveData<Boolean>()
    val comicsListDTO = mutableListOf<ComicsDTO>()
    val errorMessage = MutableLiveData<String>()

    fun fetchComicsById(id: Int) {
        viewModelScope.launch {
            when (val response = repository.fetchComicsById(id)) {
                is NetworkResponse.Success -> {
                    comicsListDTO.clear()
                    response.body.data!!.results!!.forEach {
                        if (!it.thumbnail!!.path!!.contains(ApiConstants.IMAGE_NOT_AVAILABLE))
                            comicsListDTO.add(
                                ComicsDTO(
                                    it.title!!,
                                    it.thumbnail!!.path!!,
                                )
                            )
                    }
                    fetchComicsStatus.value = true
                    if (response.body.data!!.count == 0)
                        errorMessage.postValue(ApiConstants.NO_RESULTS_FOUND)
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