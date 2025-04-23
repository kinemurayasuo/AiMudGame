package com.taewoo.aimudgame.common.converter

import com.google.gson.Gson
import jakarta.persistence.AttributeConverter

class StringMapConverter : AttributeConverter<Map<String, String>, String> {
    private val gson = Gson()
    override fun convertToDatabaseColumn(attribute: Map<String, String>?): String {
        return gson.toJson(attribute)
    }
    override fun convertToEntityAttribute(dbData: String?): Map<String, String> {
        return if (dbData == null) {
            emptyMap()
        } else {
            gson.fromJson(dbData, Map::class.java) as Map<String, String>
        }
    }
}