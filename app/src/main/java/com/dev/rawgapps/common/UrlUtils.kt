package com.dev.rawgapps.common

import io.ktor.http.ParametersBuilder
import io.ktor.http.URLBuilder
import io.ktor.http.Url

object UrlUtils {
    fun createRequestUrl(endPoint:String,parameterBuilder:(ParametersBuilder)->Unit={}): Url {
        val urlBuilder = URLBuilder()
        urlBuilder.path(endPoint)
        urlBuilder.parameters.apply {
            parameterBuilder(this)
        }
        return urlBuilder.build()
    }
}