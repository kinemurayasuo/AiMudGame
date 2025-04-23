package com.taewoo.aimudgame.adapter.outbound.persistence

import com.taewoo.aimudgame.domain.repository.GameSessionPort
import com.taewoo.aimudgame.domain.GameSession
import org.springframework.stereotype.Repository

@Repository
class GameSessionPersistenceAdapter (
    private val jpaRepository: JpaGameSessionRepository
) : GameSessionPort {
    override fun save(session: GameSession): GameSession {
        return jpaRepository.save(session)
    }
    override fun findById(sessionId: String): GameSession? {
        return jpaRepository.findById(sessionId).orElse(null)
    }
}