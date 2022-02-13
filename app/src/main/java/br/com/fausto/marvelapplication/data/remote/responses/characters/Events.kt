package br.com.fausto.marvelapplication.data.remote.responses.characters

data class Events(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemX>?,
    var returned: Int?
)