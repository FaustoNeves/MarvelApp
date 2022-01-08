package br.com.fausto.marvelapplication.data.remote.responses.all_heroes

data class AllMarvelHeroesResponse(
    var attributionHTML: String?,
    var attributionText: String?,
    var code: Int?,
    var copyright: String?,
    var `data`: Data?,
    var etag: String?,
    var status: String?
)