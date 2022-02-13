package br.com.fausto.marvelapplication.data.remote.responses.comics

data class Creators(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemX>?,
    var returned: Int?
)