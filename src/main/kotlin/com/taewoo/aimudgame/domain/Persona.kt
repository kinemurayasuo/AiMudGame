package com.taewoo.aimudgame.domain

import com.taewoo.aimudgame.common.converter.ListConverter
import com.taewoo.aimudgame.common.converter.StringMapConverter
import jakarta.persistence.*

typealias ElementMap = Map<String, String>
typealias SkillList = List<Skill>
typealias PassiveList = List<Passive>

@Entity
@Table(name = "personas")
data class Persona(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
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

    @Lob
    @Convert(converter = StringMapConverter::class)
    var resistances: ElementMap = mapOf(), // 예: "fire" to "strong"

    @Lob
    @Convert(converter = StringMapConverter::class)
    var weaknesses: ElementMap = mapOf(), // 예: "fire" to "weak"

    @Lob
    @Convert(converter = StringMapConverter::class)
    var strengths: ElementMap = mapOf(),

    @Lob
    @Convert(converter = StringMapConverter::class)
    var immunities: ElementMap = mapOf(),

    @Lob
    @Convert(converter = ListConverter.PassiveListConverter::class)
    var passives: PassiveList = listOf(),

    @Lob
    @Convert(converter = ListConverter.SkillListConverter::class)
    var skills: SkillList = listOf()
)