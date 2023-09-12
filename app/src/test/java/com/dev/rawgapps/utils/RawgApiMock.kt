package com.dev.rawgapps.utils

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf


class RawgApiMock {
    val engine = MockEngine {
        if (isFailed){
           return@MockEngine handleBadRequest()
        }
        handleRequest(it) ?:handleBadRequest()
    }
    var isFailed = false
    private fun MockRequestHandleScope.handleRequest(
        request: HttpRequestData
    ): HttpResponseData? {
        var content = ""
        if (request.url.encodedPath.contains("games")) {
            //list
            content = ResourceUtils.getResource2String("games.json")?:""
        }
        if (request.url.encodedPath.contains("search=zelda")){
            content = ResourceUtils.getResource2String("games-search-zelda.json")?:""
        }
        if (request.url.encodedPath.contains("games/forza-motorsport-2020")) {
            //detail
            content = ResourceUtils.getResource2String("forza-game-detail.json")?:""
        }

        return respond(
            content = content,
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        )

    }

    private fun MockRequestHandleScope.handleBadRequest(): HttpResponseData {

        return respond(
            content = "",
            status = HttpStatusCode.BadRequest,
            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        )

    }

}

