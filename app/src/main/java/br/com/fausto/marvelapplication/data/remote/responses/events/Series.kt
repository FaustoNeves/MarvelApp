package br.com.fausto.marvelapplication.data.remote.responses.events

data class Series(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemXXX>?,
    var returned: Int?
)