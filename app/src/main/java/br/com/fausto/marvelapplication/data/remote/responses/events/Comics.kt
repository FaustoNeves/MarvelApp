package br.com.fausto.marvelapplication.data.remote.responses.events

data class Comics(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemX>?,
    var returned: Int?
)