package com.taewoo.aimudgame.adapter.outbound.persistence

import com.taewoo.aimudgame.domain.GameSession
import org.springframework.data.jpa.repository.JpaRepository

interface JpaGameSessionRepository : JpaRepository<GameSession, String>