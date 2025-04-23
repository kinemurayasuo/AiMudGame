package com.taewoo.aimudgame.common.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.taewoo.aimudgame.domain.Passive
import com.taewoo.aimudgame.domain.Skill
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

class ListConverter {
    @Converter
    class PassiveListConverter : AttributeConverter<List<Passive>, String> {
        private val gson = Gson()

        override fun convertToDatabaseColumn(attribute: List<Passive>?): String {
            return gson.toJson(attribute)
        }

        override fun convertToEntityAttribute(dbData: String?): List<Passive> {
            return if (dbData.isNullOrEmpty()) emptyList()
            else gson.fromJson(dbData, object : TypeToken<List<Passive>>() {}.type)
        }
    }

    @Converter
    class SkillListConverter : AttributeConverter<List<Skill>, String> {
        private val gson = Gson()

        override fun convertToDatabaseColumn(attribute: List<Skill>?): String {
            return gson.toJson(attribute)
        }

        override fun convertToEntityAttribute(dbData: String?): List<Skill> {
            return if (dbData.isNullOrEmpty()) emptyList()
            else gson.fromJson(dbData, object : TypeToken<List<Skill>>() {}.type)
        }
    }
}