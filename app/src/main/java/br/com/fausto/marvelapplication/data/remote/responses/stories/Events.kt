package br.com.fausto.marvelapplication.data.remote.responses.stories

data class Events(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemXXX>?,
    var returned: Int?
)