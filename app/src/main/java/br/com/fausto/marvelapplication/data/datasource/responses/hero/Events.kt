package br.com.fausto.marvelapplication.data.datasource.responses.hero

data class Events(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemX>?,
    var returned: Int?
)