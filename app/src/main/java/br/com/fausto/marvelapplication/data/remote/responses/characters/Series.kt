package br.com.fausto.marvelapplication.data.remote.responses.characters

data class Series(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemXX>?,
    var returned: Int?
)