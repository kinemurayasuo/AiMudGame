package com.taewoo.aimudgame.adapter.inbound

import com.taewoo.aimudgame.application.GameSessionService
import com.taewoo.aimudgame.domain.Persona
import com.yourproject.domain.GameSession
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/session")
class GameSessionController(
    private val gameSessionService : GameSessionService
) {
    // 세션 시작
    @PostMapping("/start")
    fun startSession(@RequestBody persona: Persona): ResponseEntity<GameSession> {
        val session = GameSession(sessionId = UUID.randomUUID().toString(), persona = persona)
        return ResponseEntity.ok(gameSessionService.createSession(persona))
    }

    @PostMapping("/{sessionId}/next")
    fun nextTurn(
       @PathVariable sessionId: String,
       @RequestBody request: Map<String, String>
    ) : ResponseEntity<GameSession> {
        val updated = gameSessionService.updateTurn(
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
        val session = gameSessionService.getSession(sessionId)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(session)
    }
}