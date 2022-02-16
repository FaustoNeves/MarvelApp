package br.com.fausto.marvelapplication.ui.constants

class GeneralConstants {
    companion object {
        const val MARVEL_OFFICIAL_WEBSITE_CHARACTERS = "https://www.marvel.com/characters"
        const val CHARACTER_THUMBNAIL_SIZE = "/portrait_uncanny"
        const val INITIAL_QUERY_PARAMETER_SEARCH = "A"
        const val THUMBNAIL_IMAGE_TYPE = ".jpg"
        const val THUMBNAIL_FULL_SIZE = "/detail.jpg"
    }
}

class NavigationConstants {
    companion object {
        const val CHARACTERS_FRAGMENT = "main_screen"
        const val CATEGORY_SELECTION_FRAGMENT = "selection_screen"
        const val COMICS_SCREEN_FRAGMENT = "comics_screen"
    }
}

class BundleConstants {
    companion object {
        const val URL_DETAIL = "urlDetail"
        const val CHARACTER_ID = "characterId"
        const val IMAGE_PATH = "imagePath"
        const val CHARACTER_NAME = "characterName"
        const val CHARACTER_DESCRIPTION = "characterDescription"
    }

}

class ApiConstants {
    companion object {
        const val IMAGE_NOT_AVAILABLE = "image_not_available"
        const val NO_DESCRIPTION_AVAILABLE = "No description available"
        const val NO_RESULTS_FOUND = "No results found"
        const val EMPTY_SEARCH_FIELD = "Empty search field"
        const val CONNECTION_TIMEOUT = "Check your internet connection"
        const val UNAUTHORIZED_REQUEST = "Unauthorized request"
    }
}
