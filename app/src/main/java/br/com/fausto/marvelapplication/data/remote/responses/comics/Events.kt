package br.com.fausto.marvelapplication.data.remote.responses.comics

data class Events(
    var available: Int?,
    var collectionURI: String?,
    var items: List<Any>?,
    var returned: Int?
)