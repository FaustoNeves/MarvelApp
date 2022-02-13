package br.com.fausto.marvelapplication.data.remote.responses.comics

data class Stories(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemXX>?,
    var returned: Int?
)