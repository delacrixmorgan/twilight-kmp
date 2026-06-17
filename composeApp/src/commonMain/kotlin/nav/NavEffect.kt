package nav

sealed interface NavEffect {
    data class OpenUri(val url: String) : NavEffect
}
