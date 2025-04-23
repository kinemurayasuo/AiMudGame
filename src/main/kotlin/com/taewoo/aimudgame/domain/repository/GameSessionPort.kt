package com.taewoo.aimudgame.domain.repository

import com.yourproject.domain.GameSession

interface GameSessionPort {
    fun save(session: GameSession): GameSession
    fun findById(sessionId: String): GameSession?

}