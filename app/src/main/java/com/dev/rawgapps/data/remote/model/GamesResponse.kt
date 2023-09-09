package com.dev.rawgapps.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GamesResponse(
    @SerialName("count")
    val count: Int?=null,
    @SerialName("description")
    val description: String?=null,
    @SerialName("filters")
    val filters: Filters?=null,
    @SerialName("next")
    val next: String?=null,
    @SerialName("nofollow")
    val nofollow: Boolean?=null,
    @SerialName("nofollow_collections")
    val nofollowCollections: List<String>?=null,
    @SerialName("noindex")
    val noindex: Boolean?=null,
    @SerialName("previous")
    val previous: String?=null,
    @SerialName("results")
    val results: List<Result>?=null,
    @SerialName("seo_description")
    val seoDescription: String?=null,
    @SerialName("seo_h1")
    val seoH1: String?=null,
    @SerialName("seo_keywords")
    val seoKeywords: String?=null,
    @SerialName("seo_title")
    val seoTitle: String?=null
)

@Serializable
data class Filters(
    @SerialName("years")
    val years: List<Year>?=null
)

@Serializable
data class Result(
    @SerialName("added")
    val added: Int?=null,
    @SerialName("added_by_status")
    val addedByStatus: AddedByStatus?=null,
    @SerialName("background_image")
    val backgroundImage: String?=null,
    @SerialName("dominant_color")
    val dominantColor: String?=null,
    @SerialName("esrb_rating")
    val esrbRating: EsrbRating?=null,
    @SerialName("genres")
    val genres: List<Genre>?=null,
    @SerialName("id")
    val id: Int?=null,
    @SerialName("metacritic")
    val metacritic: Int?=null,
    @SerialName("name")
    val name: String?=null,
    @SerialName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>?=null,
    @SerialName("platforms")
    val platforms: List<PlatformX>?=null,
    @SerialName("playtime")
    val playtime: Int?=null,
    @SerialName("rating")
    val rating: Double?,
    @SerialName("rating_top")
    val ratingTop: Int?=null,
    @SerialName("ratings")
    val ratings: List<Rating>?=null,
    @SerialName("ratings_count")
    val ratingsCount: Int?=null,
    @SerialName("released")
    val released: String?=null,
    @SerialName("reviews_count")
    val reviewsCount: Int?=null,
    @SerialName("reviews_text_count")
    val reviewsTextCount: Int?=null,
    @SerialName("saturated_color")
    val saturatedColor: String?=null,
    @SerialName("short_screenshots")
    val shortScreenshots: List<ShortScreenshot>?=null,
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
    @SerialName("updated")
    val updated: String?=null,
)

@Serializable
data class Year(
    @SerialName("count")
    val count: Int?=null,
    @SerialName("decade")
    val decade: Int?=null,
    @SerialName("filter")
    val filter: String?=null,
    @SerialName("from")
    val from: Int?=null,
    @SerialName("nofollow")
    val nofollow: Boolean?=null,
    @SerialName("to")
    val to: Int?=null,
    @SerialName("years")
    val years: List<YearX>?=null
)

@Serializable
data class YearX(
    @SerialName("count")
    val count: Int?=null,
    @SerialName("nofollow")
    val nofollow: Boolean?=null,
    @SerialName("year")
    val year: Int?=null
)

@Serializable
data class AddedByStatus(
    @SerialName("beaten")
    val beaten: Int?=null,
    @SerialName("dropped")
    val dropped: Int?=null,
    @SerialName("owned")
    val owned: Int?=null,
    @SerialName("playing")
    val playing: Int?=null,
    @SerialName("toplay")
    val toplay: Int?=null,
    @SerialName("yet")
    val yet: Int?=null
)

@Serializable
data class EsrbRating(
    @SerialName("id")
    val id: Int?=null,
    @SerialName("name")
    val name: String?=null,
    @SerialName("slug")
    val slug: String?=null
)

@Serializable
data class Genre(
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
data class ParentPlatform(
    @SerialName("platform")
    val platform: Platform?=null
)

@Serializable
data class PlatformX(
    @SerialName("platform")
    val platform: PlatformXX?=null,
    @SerialName("released_at")
    val releasedAt: String?=null,
    @SerialName("requirements_en")
    val requirementsEn: Requirements?=null,
    @SerialName("requirements_ru")
    val requirementsRu: Requirements?=null,
    @SerialName("requirements")
    val requirements: Requirements? =null
)

@Serializable
data class Rating(
    @SerialName("count")
    val count: Int?=null,
    @SerialName("id")
    val id: Int?=null,
    @SerialName("percent")
    val percent: Double?=null,
    @SerialName("title")
    val title: String?=null
)

@Serializable
data class ShortScreenshot(
    @SerialName("id")
    val id: Int?=null,
    @SerialName("image")
    val image: String?=null
)

@Serializable
data class Store(
    @SerialName("id")
    val id: Int?=null,
    @SerialName("store")
    val store: StoreX?=null
)

@Serializable
data class Tag(
    @SerialName("games_count")
    val gamesCount: Int?=null,
    @SerialName("id")
    val id: Int?=null,
    @SerialName("image_background")
    val imageBackground: String?=null,
    @SerialName("language")
    val language: String?=null,
    @SerialName("name")
    val name: String?=null,
    @SerialName("slug")
    val slug: String?=null
)

@Serializable
data class Platform(
    @SerialName("id")
    val id: Int?=null,
    @SerialName("name")
    val name: String?=null,
    @SerialName("slug")
    val slug: String?=null
)

@Serializable
data class PlatformXX(
    @SerialName("games_count")
    val gamesCount: Int?=null,
    @SerialName("id")
    val id: Int?=null,
    @SerialName("image")
    val image: String?=null,
    @SerialName("image_background")
    val imageBackground: String?=null,
    @SerialName("name")
    val name: String?=null,
    @SerialName("slug")
    val slug: String?=null,
    @SerialName("year_end")
    val yearEnd: Int?=null,
    @SerialName("year_start")
    val yearStart: Int?=null
)

@Serializable
data class StoreX(
    @SerialName("domain")
    val domain: String?=null,
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