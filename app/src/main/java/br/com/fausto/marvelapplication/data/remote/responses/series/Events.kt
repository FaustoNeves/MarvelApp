package br.com.fausto.marvelapplication.data.remote.responses.series

data class Events(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemXXX>?,
    var returned: Int?
)