package br.com.fausto.marvelapplication.data.datasource.remote.responses.all_heroes

data class Events(
    var available: Int?,
    var collectionURI: String?,
    var items: List<ItemX>?,
    var returned: Int?
)