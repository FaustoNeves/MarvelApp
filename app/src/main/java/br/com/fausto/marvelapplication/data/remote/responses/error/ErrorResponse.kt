package br.com.fausto.marvelapplication.data.remote.responses.error

data class ErrorResponse(
    var code: Int?,
    var status: String?,
    var message: String?
)