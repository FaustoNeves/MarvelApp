package br.com.fausto.marvelapplication.data.remote.responses.characters

data class Stories(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemXXX>?,
    var returned: Int?
)