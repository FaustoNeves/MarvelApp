package br.com.fausto.marvelapplication.ui.screens.categories.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fausto.marvelapplication.data.dtos.CategoryDTO
import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.remote.responses.category.CategoryResponse
import br.com.fausto.marvelapplication.data.remote.responses.error.ErrorResponse
import br.com.fausto.marvelapplication.data.repository.IMarvelRepository
import br.com.fausto.marvelapplication.ui.constants.ApiConstants
import br.com.fausto.marvelapplication.ui.constants.CategoriesConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: IMarvelRepository
) : ViewModel() {

    val fetchCategoryStatus = MutableLiveData<Boolean>()
    val categoriesListDTO = mutableListOf<CategoryDTO>()
    val errorMessage = MutableLiveData<String>()
    private lateinit var response: NetworkResponse<CategoryResponse, ErrorResponse>

    fun fetchContentById(id: Int, selectedCategory: String) {

        viewModelScope.launch {
            when (selectedCategory) {
                CategoriesConstants.COMICS -> response = repository.fetchComicsById(id)
                CategoriesConstants.SERIES -> response = repository.fetchSeriesById(id)
            }
            when (response) {
                is NetworkResponse.Success -> {
                    categoriesListDTO.clear()
                    (response as NetworkResponse.Success<CategoryResponse>).body.data!!.results!!.forEach {
                        if (!it.thumbnail!!.path!!.contains(ApiConstants.IMAGE_NOT_AVAILABLE))
                            categoriesListDTO.add(
                                CategoryDTO(
                                    it.title!!,
                                    it.thumbnail!!.path!!,
                                )
                            )
                    }
                    fetchCategoryStatus.value = true
                    if ((response as NetworkResponse.Success<CategoryResponse>).body.data!!.count == 0)
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