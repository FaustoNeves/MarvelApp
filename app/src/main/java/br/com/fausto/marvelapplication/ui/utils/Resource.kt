package br.com.fausto.marvelapplication.ui.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?)
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}