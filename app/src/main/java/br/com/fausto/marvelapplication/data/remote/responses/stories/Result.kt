package br.com.fausto.marvelapplication.data.remote.responses.stories

data class Result(
    var characters: Characters?,
    var comics: Comics?,
    var creators: Creators?,
    var description: String?,
    var events: Events?,
    var id: Int?,
    var modified: String?,
    var originalIssue: OriginalIssue?,
    var resourceURI: String?,
    var series: Series?,
    var thumbnail: Any?,
    var title: String?,
    var type: String?
)