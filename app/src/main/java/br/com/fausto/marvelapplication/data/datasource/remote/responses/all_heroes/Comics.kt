package br.com.fausto.marvelapplication.data.datasource.remote.responses.all_heroes

data class Comics(
    var available: Int?,
    var collectionURI: String?,
    var items: List<Item>?,
    var returned: Int?
)