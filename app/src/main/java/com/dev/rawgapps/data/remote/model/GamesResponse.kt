package com.dev.rawgapps.data.remote.model
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class GamesResponse(
    @SerialName("count")
    val count: Int?,
    @SerialName("description")
    val description: String?,
    @SerialName("filters")
    val filters: Filters?,
    @SerialName("next")
    val next: String?,
    @SerialName("nofollow")
    val nofollow: Boolean?,
    @SerialName("nofollow_collections")
    val nofollowCollections: List<String>?,
    @SerialName("noindex")
    val noindex: Boolean?,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<Result>?,
    @SerialName("seo_description")
    val seoDescription: String?,
    @SerialName("seo_h1")
    val seoH1: String?,
    @SerialName("seo_keywords")
    val seoKeywords: String?,
    @SerialName("seo_title")
    val seoTitle: String?
)

@Serializable
data class Filters(
    @SerialName("years")
    val years: List<Year>?
)

@Serializable
data class Result(
    @SerialName("added")
    val added: Int?,
    @SerialName("added_by_status")
    val addedByStatus: AddedByStatus?,
    @SerialName("background_image")
    val backgroundImage: String?,
    @SerialName("dominant_color")
    val dominantColor: String?,
    @SerialName("esrb_rating")
    val esrbRating: EsrbRating?,
    @SerialName("genres")
    val genres: List<Genre>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("metacritic")
    val metacritic: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>?,
    @SerialName("platforms")
    val platforms: List<PlatformX>?,
    @SerialName("playtime")
    val playtime: Int?,
    @SerialName("rating")
    val rating: Double?,
    @SerialName("rating_top")
    val ratingTop: Int?,
    @SerialName("ratings")
    val ratings: List<Rating>?,
    @SerialName("ratings_count")
    val ratingsCount: Int?,
    @SerialName("released")
    val released: String?,
    @SerialName("reviews_count")
    val reviewsCount: Int?,
    @SerialName("reviews_text_count")
    val reviewsTextCount: Int?,
    @SerialName("saturated_color")
    val saturatedColor: String?,
    @SerialName("short_screenshots")
    val shortScreenshots: List<ShortScreenshot>?,
    @SerialName("slug")
    val slug: String?,
    @SerialName("stores")
    val stores: List<Store>?,
    @SerialName("suggestions_count")
    val suggestionsCount: Int?,
    @SerialName("tags")
    val tags: List<Tag>?,
    @SerialName("tba")
    val tba: Boolean?,
    @SerialName("updated")
    val updated: String?,
)

@Serializable
data class Year(
    @SerialName("count")
    val count: Int?,
    @SerialName("decade")
    val decade: Int?,
    @SerialName("filter")
    val filter: String?,
    @SerialName("from")
    val from: Int?,
    @SerialName("nofollow")
    val nofollow: Boolean?,
    @SerialName("to")
    val to: Int?,
    @SerialName("years")
    val years: List<YearX>?
)

@Serializable
data class YearX(
    @SerialName("count")
    val count: Int?,
    @SerialName("nofollow")
    val nofollow: Boolean?,
    @SerialName("year")
    val year: Int?
)

@Serializable
data class AddedByStatus(
    @SerialName("beaten")
    val beaten: Int?,
    @SerialName("dropped")
    val dropped: Int?,
    @SerialName("owned")
    val owned: Int?,
    @SerialName("playing")
    val playing: Int?,
    @SerialName("toplay")
    val toplay: Int?,
    @SerialName("yet")
    val yet: Int?
)

@Serializable
data class EsrbRating(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("slug")
    val slug: String?
)

@Serializable
data class Genre(
    @SerialName("games_count")
    val gamesCount: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image_background")
    val imageBackground: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("slug")
    val slug: String?
)

@Serializable
data class ParentPlatform(
    @SerialName("platform")
    val platform: Platform?
)

@Serializable
data class PlatformX(
    @SerialName("platform")
    val platform: PlatformXX?,
    @SerialName("released_at")
    val releasedAt: String?,
    @SerialName("requirements_en")
    val requirementsEn: RequirementsEn?,
    @SerialName("requirements_ru")
    val requirementsRu: RequirementsRu?
)

@Serializable
data class Rating(
    @SerialName("count")
    val count: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("percent")
    val percent: Double?,
    @SerialName("title")
    val title: String?
)

@Serializable
data class ShortScreenshot(
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?
)

@Serializable
data class Store(
    @SerialName("id")
    val id: Int?,
    @SerialName("store")
    val store: StoreX?
)

@Serializable
data class Tag(
    @SerialName("games_count")
    val gamesCount: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image_background")
    val imageBackground: String?,
    @SerialName("language")
    val language: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("slug")
    val slug: String?
)

@Serializable
data class Platform(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("slug")
    val slug: String?
)

@Serializable
data class PlatformXX(
    @SerialName("games_count")
    val gamesCount: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("image_background")
    val imageBackground: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("slug")
    val slug: String?,
    @SerialName("year_end")
    val yearEnd: Int?,
    @SerialName("year_start")
    val yearStart: Int?
)

@Serializable
data class RequirementsEn(
    @SerialName("minimum")
    val minimum: String?,
    @SerialName("recommended")
    val recommended: String?
)

@Serializable
data class RequirementsRu(
    @SerialName("minimum")
    val minimum: String?,
    @SerialName("recommended")
    val recommended: String?
)

@Serializable
data class StoreX(
    @SerialName("domain")
    val domain: String?,
    @SerialName("games_count")
    val gamesCount: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image_background")
    val imageBackground: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("slug")
    val slug: String?
)