package com.taewoo.aimudgame.adapter.inbound.dto

// 게임 시작 요청 DTO
data class StartGameRequest(
    val name: String,
    val age: Int,
    val gender: String,
    val trait: String
    // 다른 초기 페르소나 정보들...
)

// 턴 진행 요청 DTO
data class UpdateTurnRequest(
    val log: String,
    val choice: String
)

// 응답 DTO들
data class PersonaResponse(
    val id: Long,
    val name: String,
    val age: Int,
    val gender: String,
    val trait: String,
    var level: Int,
    var hp: Int,
    var mp: Int,
    var strength: Int,
    var magic: Int,
    var endurance: Int,
    var agility: Int,
    var luck: Int,
    val skills: List<String>, // Skill 객체의 이름만 리스트로 변환
    val passives: List<String> // Passive 객체의 이름만 리스트로 변환
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
