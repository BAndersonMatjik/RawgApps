package com.dev.rawgapps.utils

import com.dev.rawgapps.common.JsonUtils
import com.dev.rawgapps.data.remote.model.GamesResponse
import kotlinx.serialization.decodeFromString
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors

object ResourceUtils {
    fun getResource2String(fileName:String): String? {
        return javaClass.classLoader?.getResourceAsStream(fileName)?.let {
            val inputStream = InputStreamReader(it, Charsets.UTF_8)
            val bufferReader = BufferedReader(inputStream)
            val result = bufferReader.lines().parallel().collect(Collectors.joining())
            inputStream.close()
            result
        }
    }



    fun getResource2Bean(fileName:String): GamesResponse? {
        return javaClass.classLoader?.getResourceAsStream(fileName)?.let {
            val inputStream = InputStreamReader(it, Charsets.UTF_8)
            val bufferReader = BufferedReader(inputStream)
            val result = bufferReader.lines().parallel().collect(Collectors.joining())
            val resultBean = JsonUtils.json.decodeFromString<GamesResponse>(result)
            inputStream.close()
            resultBean
        }
    }
}