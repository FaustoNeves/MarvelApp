package br.com.fausto.marvelapplication.data.remote.responses.series

data class SeriesResponse(
    var attributionHTML: String?,
    var attributionText: String?,
    var code: Int?,
    var copyright: String?,
    var `data`: Data?,
    var etag: String?,
    var status: String?
)