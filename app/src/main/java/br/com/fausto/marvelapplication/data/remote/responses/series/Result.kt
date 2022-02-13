package br.com.fausto.marvelapplication.data.remote.responses.series

data class Result(
    var characters: Characters?,
    var comics: Comics?,
    var creators: Creators?,
    var description: Any?,
    var endYear: Int?,
    var events: Events?,
    var id: Int?,
    var modified: String?,
    var next: Any?,
    var previous: Any?,
    var rating: String?,
    var resourceURI: String?,
    var startYear: Int?,
    var stories: Stories?,
    var thumbnail: Thumbnail?,
    var title: String?,
    var type: String?,
    var urls: List<Url>?
)