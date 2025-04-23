package com.taewoo.aimudgame.domain

import com.taewoo.aimudgame.common.converter.ListConverter
import com.taewoo.aimudgame.common.converter.StringMapConverter
import jakarta.persistence.Convert
import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

typealias ElementMap = Map<String, String>
typealias SkillList = List<Skill>
typealias PassiveList = List<Passive>

@Entity
data class Persona(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val gender: String,
    val trait: String,
    var level: Int = 1,
    var hp: Int = 10,
    var mp: Int = 5,
    var strength: Int = 0,
    var magic: Int = 0,
    var endurance: Int = 0,
    var agility: Int = 0,
    var luck: Int = 0,
    @Convert(converter = StringMapConverter::class)
    var resistances: ElementMap = mapOf(),
    @Convert(converter = StringMapConverter::class)// 예: "fire" to "weak"
    var weaknesses: ElementMap = mapOf(),
    @Convert(converter = StringMapConverter::class)
    var strengths: ElementMap = mapOf(),
    @Convert(converter = StringMapConverter::class)
    var immunities: ElementMap = mapOf(),
    @Convert(converter = ListConverter.PassiveListConverter::class)
    var passives: PassiveList = listOf(),
    @Convert(converter = ListConverter.SkillListConverter::class)
    var skills: SkillList = listOf()
)



