package com.taewoo.aimudgame.adapter.inbound.dto

// ============= Request DTOs =============

data class StartGameRequest(
    val name: String,
    val age: Int,
    val gender: String,
    val trait: String
)

data class UpdateTurnRequest(
    val log: String,
    val choice: String
)

// ============= Response DTOs =============

data class PersonaResponse(
    val id: Long,
    val name: String,
    val age: Int,
    val gender: String,
    val trait: String,
    val level: Int,
    val hp: Int,
    val mp: Int,
    val strength: Int,
    val magic: Int,
    val endurance: Int,
    val agility: Int,
    val luck: Int,
    val skills: List<String>,
    val passives: List<String>
)

data class GameSessionResponse(
    val sessionId: String,
    val turn: Int,
    val persona: PersonaResponse,
    val logHistory: List<String>,
    val choiceHistory: List<String>,
    val assignedClass: String?,
    val ending: String?
)