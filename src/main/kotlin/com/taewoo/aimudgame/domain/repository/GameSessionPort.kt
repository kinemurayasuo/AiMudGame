package com.taewoo.aimudgame.domain.repository

import com.taewoo.aimudgame.domain.GameSession

interface GameSessionPort {
    fun save(session: GameSession): GameSession
    fun findById(sessionId: String): GameSession?

}