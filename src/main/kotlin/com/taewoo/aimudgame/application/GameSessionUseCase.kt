package com.taewoo.aimudgame.application

import com.taewoo.aimudgame.domain.Persona
import com.yourproject.domain.GameSession
import java.awt.Choice

interface GameSessionUseCase {
    fun createSession(persona: Persona): GameSession
    fun getSession(sessionId: String): GameSession?
    fun updateTurn(sessionId: String, log: String, choice: String) : GameSession?
}