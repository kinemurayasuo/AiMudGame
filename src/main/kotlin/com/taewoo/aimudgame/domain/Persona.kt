package com.taewoo.aimudgame.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

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
    var resistances: Map<String, String> = mapOf(),  // 예: "fire" to "weak"
    var weaknesses: Map<String, String> = mapOf(),
    var strengths: Map<String, String> = mapOf(),
    var immunities: Map<String, String> = mapOf(),
    var passives: List<Passive> = listOf(),
    var skills: List<Skill> = listOf()
)



