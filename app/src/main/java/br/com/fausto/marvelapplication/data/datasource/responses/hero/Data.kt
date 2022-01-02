package br.com.fausto.marvelapplication.data.datasource.responses.hero

data class Data(
    var count: Int?,
    var limit: Int?,
    var offset: Int?,
    var results: List<Result>?,
    var total: Int?
)