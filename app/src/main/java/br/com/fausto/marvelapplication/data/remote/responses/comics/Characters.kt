package br.com.fausto.marvelapplication.data.remote.responses.comics

data class Characters(
    var available: Int?,
    var collectionURI: String?,
    var items: List<Item>?,
    var returned: Int?
)