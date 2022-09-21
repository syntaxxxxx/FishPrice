package com.aej.efisheryandroidassessment.common

import com.google.gson.Gson
import java.io.InputStreamReader
import java.lang.reflect.Type

fun <T> loadJson(clazz: Class<T>, file: String): T {
    val inputStreamReader = InputStreamReader(clazz.classLoader?.getResourceAsStream(file))
    return Gson().fromJson(inputStreamReader, clazz).apply {
    }
}

fun <T> loadEmptyObjectListJson(classLoader: ClassLoader?, file: String, type: Type): List<T> {
    val string = classLoader?.getResource(file)?.readText()
    return Gson().fromJson(string, type)
}

//fun <T>String.toDataList(): List<T> {
//    val type = object : TypeToken<List<T>>() {}.type
//    return Gson().fromJson(this, type)
//}