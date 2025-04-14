package com.taewoo.aimudgame.adapter.inbound

import com.taewoo.aimudgame.application.GameSessionService
import com.taewoo.aimudgame.domain.Persona
import com.yourproject.domain.GameSession
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/session")
class GameSessionController(
    private val gameSession: GameSessionService
) {
    // 세션 시작
    @PostMapping("/start")
    fun startSession(@RequestBody persona: Persona): ResponseEntity<GameSession> {
        return ResponseEntity.ok(gameSession.createSession(persona))
    }

    @PostMapping("/{sessionId}/next")
    fun nextTurn(
       @PathVariable sessionId: String,
       @RequestBody request: Map<String, String>
    ) : ResponseEntity<GameSession> {
        val updated = gameSession.updateTurn(
            sessionId,
            request["log"] ?: "",
            request["choice"] ?: ""
        ) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(updated)
    }

    @GetMapping("/{sessionId}")
    fun getSession(
        @PathVariable sessionId: String
    ): ResponseEntity<GameSession> {
        return gameSession.getSession(sessionId)
            ?.let { ResponseEntity.ok(it) }
            ?: return ResponseEntity.notFound().build()
    }
}