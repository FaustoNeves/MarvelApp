package br.com.fausto.marvelapplication.data.datasource.remote.responses.all_heroes

data class Data(
    var count: Int?,
    var limit: Int?,
    var offset: Int?,
    var results: List<Result>?,
    var total: Int?
)