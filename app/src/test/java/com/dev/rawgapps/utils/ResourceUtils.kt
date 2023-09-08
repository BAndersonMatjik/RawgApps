package com.dev.rawgapps.utils

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
}