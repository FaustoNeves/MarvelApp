package br.com.fausto.marvelapplication.data.remote.responses.characters

data class Comics(
    var available: Int?,
    var collectionURI: String?,
    var items: List<Item>?,
    var returned: Int?
)