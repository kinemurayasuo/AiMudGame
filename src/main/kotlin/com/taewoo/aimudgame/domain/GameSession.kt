package com.yourproject.domain

import com.taewoo.aimudgame.domain.Persona
import java.util.UUID

data class GameSession(
    val sessionId: String = UUID.randomUUID().toString(),
    var turn: Int = 1,
    val persona: Persona,
    val logHistory: MutableList<String> = mutableListOf(),
    val choiceHistory: MutableList<String> = mutableListOf(),
    var assignedClass: String? = null,
    var ending: String? = null
)
