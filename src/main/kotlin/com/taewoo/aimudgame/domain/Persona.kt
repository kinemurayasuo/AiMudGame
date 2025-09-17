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

    @Column(nullable = false)
    val age: Int,

    @Column(nullable = false, length = 10)
    val gender: String,

    @Column(nullable = false, length = 100)
    val trait: String,

    @Column(nullable = false)
    var level: Int = 1,

    @Column(nullable = false)
    var hp: Int = 10,

    @Column(nullable = false)
    var mp: Int = 5,

    @Column(nullable = false)
    var strength: Int = 0,

    @Column(nullable = false)
    var magic: Int = 0,

    @Column(nullable = false)
    var endurance: Int = 0,

    @Column(nullable = false)
    var agility: Int = 0,

    @Column(nullable = false)
    var luck: Int = 0,

    @Column(name = "resistances", columnDefinition = "TEXT")
    @Convert(converter = StringMapConverter::class)
    var resistances: ElementMap = mapOf(), // 예: "fire" to "strong"

    @Column(name = "weaknesses", columnDefinition = "TEXT")
    @Convert(converter = StringMapConverter::class)
    var weaknesses: ElementMap = mapOf(), // 예: "fire" to "weak"

    @Column(name = "strengths", columnDefinition = "TEXT")
    @Convert(converter = StringMapConverter::class)
    var strengths: ElementMap = mapOf(),

    @Column(name = "immunities", columnDefinition = "TEXT")
    @Convert(converter = StringMapConverter::class)
    var immunities: ElementMap = mapOf(),

    @Column(name = "passives", columnDefinition = "TEXT")
    @Convert(converter = ListConverter.PassiveListConverter::class)
    var passives: PassiveList = listOf(),

    @Column(name = "skills", columnDefinition = "TEXT")
    @Convert(converter = ListConverter.SkillListConverter::class)
    var skills: SkillList = listOf()
)