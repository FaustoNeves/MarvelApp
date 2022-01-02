package br.com.fausto.marvelapplication.data.datasource.responses.hero

data class Comics(
    var available: Int?,
    var collectionURI: String?,
    var items: List<Item>?,
    var returned: Int?
)