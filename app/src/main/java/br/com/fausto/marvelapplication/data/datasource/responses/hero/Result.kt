package br.com.fausto.marvelapplication.data.datasource.responses.hero

data class Result(
    var comics: Comics?,
    var description: String?,
    var events: Events?,
    var id: Int?,
    var modified: String?,
    var name: String?,
    var resourceURI: String?,
    var series: Series?,
    var stories: Stories?,
    var thumbnail: Thumbnail?,
    var urls: List<Url>?
)