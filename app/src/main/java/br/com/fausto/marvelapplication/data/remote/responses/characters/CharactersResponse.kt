package br.com.fausto.marvelapplication.data.remote.responses.characters

data class CharactersResponse(
    var attributionHTML: String?,
    var attributionText: String?,
    var code: Int?,
    var copyright: String?,
    var `data`: Data?,
    var etag: String?,
    var status: String?
)