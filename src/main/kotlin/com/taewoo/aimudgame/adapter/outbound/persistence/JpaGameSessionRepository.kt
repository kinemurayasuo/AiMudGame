package com.taewoo.aimudgame.adapter.outbound.persistence

import com.yourproject.domain.GameSession
import org.springframework.data.jpa.repository.JpaRepository

interface JpaGameSessionRepository : JpaRepository<GameSession, String>