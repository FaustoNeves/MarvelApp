package br.com.fausto.marvelapplication.data.remote.responses.events

data class Result(
    var characters: Characters?,
    var comics: Comics?,
    var creators: Creators?,
    var description: String?,
    var end: String?,
    var id: Int?,
    var modified: String?,
    var next: Next?,
    var previous: Previous?,
    var resourceURI: String?,
    var series: Series?,
    var start: String?,
    var stories: Stories?,
    var thumbnail: Thumbnail?,
    var title: String?,
    var urls: List<Url>?
)