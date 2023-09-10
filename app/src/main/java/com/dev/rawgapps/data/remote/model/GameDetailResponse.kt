package com.dev.rawgapps.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailResponse(
    @SerialName("achievements_count")
    val achievementsCount: Int?=null,
    @SerialName("added")
    val added: Int?=null,
    @SerialName("added_by_status")
    val addedByStatus: AddedByStatus?=null,
    @SerialName("additions_count")
    val additionsCount: Int?=null,
    @SerialName("alternative_names")
    val alternativeNames: List<String>?=null,
    @SerialName("background_image")
    val backgroundImage: String?=null,
    @SerialName("background_image_additional")
    val backgroundImageAdditional: String?=null,
    @SerialName("creators_count")
    val creatorsCount: Int?=null,
    @SerialName("description")
    val description: String?=null,
    @SerialName("description_raw")
    val descriptionRaw: String?=null,
    @SerialName("developers")
    val developers: List<Developer>?=null,
    @SerialName("dominant_color")
    val dominantColor: String?=null,
    @SerialName("esrb_rating")
    val esrbRating: EsrbRating?=null,
    @SerialName("game_series_count")
    val gameSeriesCount: Int?=null,
    @SerialName("genres")
    val genres: List<Genre>?=null,
    @SerialName("id")
    val id: Int?=null,
    @SerialName("metacritic")
    val metacritic: Int?=null,
    @SerialName("metacritic_url")
    val metacriticUrl: String?=null,
    @SerialName("movies_count")
    val moviesCount: Int?=null,
    @SerialName("name")
    val name: String?=null,
    @SerialName("name_original")
    val nameOriginal: String?=null,
    @SerialName("parent_achievements_count")
    val parentAchievementsCount: Int?=null,
    @SerialName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>?=null,
    @SerialName("parents_count")
    val parentsCount: Int?=null,
    @SerialName("platforms")
    val platforms: List<PlatformX>?=null,
    @SerialName("playtime")
    val playtime: Int?=null,
    @SerialName("publishers")
    val publishers: List<Publisher>?=null,
    @SerialName("rating")
    val rating: Double?=null,
    @SerialName("rating_top")
    val ratingTop: Int?=null,
    @SerialName("ratings")
    val ratings: List<Rating>?=null,
    @SerialName("ratings_count")
    val ratingsCount: Int?=null,
    @SerialName("reactions")
    val reactions: Reactions?=null,
    @SerialName("reddit_count")
    val redditCount: Int?=null,
    @SerialName("reddit_description")
    val redditDescription: String?=null,
    @SerialName("reddit_logo")
    val redditLogo: String?=null,
    @SerialName("reddit_name")
    val redditName: String?=null,
    @SerialName("reddit_url")
    val redditUrl: String?=null,
    @SerialName("released")
    val released: String?=null,
    @SerialName("reviews_count")
    val reviewsCount: Int?=null,
    @SerialName("reviews_text_count")
    val reviewsTextCount: Int?=null,
    @SerialName("saturated_color")
    val saturatedColor: String?=null,
    @SerialName("screenshots_count")
    val screenshotsCount: Int?=null,
    @SerialName("slug")
    val slug: String?=null,
    @SerialName("stores")
    val stores: List<Store>?=null,
    @SerialName("suggestions_count")
    val suggestionsCount: Int?=null,
    @SerialName("tags")
    val tags: List<Tag>?=null,
    @SerialName("tba")
    val tba: Boolean?=null,
    @SerialName("twitch_count")
    val twitchCount: Int?=null,
    @SerialName("updated")
    val updated: String?=null,
    @SerialName("user_game")
    val userGame: String?=null,
    @SerialName("website")
    val website: String?=null,
    @SerialName("youtube_count")
    val youtubeCount: Int?=null
)


@Serializable
data class Developer(
    @SerialName("games_count")
    val gamesCount: Int?=null,
    @SerialName("id")
    val id: Int?=null,
    @SerialName("image_background")
    val imageBackground: String?=null,
    @SerialName("name")
    val name: String?=null,
    @SerialName("slug")
    val slug: String?=null
)

@Serializable
data class Publisher(
    @SerialName("games_count")
    val gamesCount: Int?=null,
    @SerialName("id")
    val id: Int?=null,
    @SerialName("image_background")
    val imageBackground: String?=null,
    @SerialName("name")
    val name: String?=null,
    @SerialName("slug")
    val slug: String?=null
)


@Serializable
data class Reactions(
    @SerialName("1")
    val x1: Int?=null,
    @SerialName("10")
    val x10: Int?=null,
    @SerialName("11")
    val x11: Int?=null,
    @SerialName("12")
    val x12: Int?=null,
    @SerialName("3")
    val x3: Int?=null,
    @SerialName("4")
    val x4: Int?=null,
    @SerialName("9")
    val x9: Int?=null
)


@Serializable
data class Requirements(
    @SerialName("minimum")
    val minimum: String?=null,
    @SerialName("recommended")
    val recommended: String?=null
)
