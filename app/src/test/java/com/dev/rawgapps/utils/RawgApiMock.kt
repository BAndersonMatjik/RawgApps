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
        handleRequest(it) ?:handleBadRequest()
    }

    private fun MockRequestHandleScope.handleRequest(
        request: HttpRequestData
    ): HttpResponseData? {
        var content = ""
        if (request.url.encodedPath.contains("games").not()) {
            return null
        }

        if (request.url.encodedPath.contains("games/")) {
            //detail
        }
        if (request.url.encodedPath.contains("games")) {
            //list
            content = ResourceUtils.getResource2String("games.json")?:""
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

