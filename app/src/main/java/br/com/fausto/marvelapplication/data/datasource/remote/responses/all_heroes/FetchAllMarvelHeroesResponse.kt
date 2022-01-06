package br.com.fausto.marvelapplication.data.datasource.remote.responses.all_heroes

data class FetchAllMarvelHeroesResponse(
    var attributionHTML: String?,
    var attributionText: String?,
    var code: Int?,
    var copyright: String?,
    var `data`: Data?,
    var etag: String?,
    var status: String?
)