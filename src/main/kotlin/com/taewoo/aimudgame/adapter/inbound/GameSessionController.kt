package com.taewoo.aimudgame.adapter.inbound

import com.taewoo.aimudgame.adapter.inbound.dto.StartGameRequest
import com.taewoo.aimudgame.adapter.inbound.dto.UpdateTurnRequest
import com.taewoo.aimudgame.adapter.inbound.dto.toResponse
import com.taewoo.aimudgame.application.GameSessionUseCase
import com.taewoo.aimudgame.domain.Persona
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sessions")
class GameSessionController(
    private val gameSessionUseCase: GameSessionUseCase
) {

    @PostMapping("/start")
    fun startSession(@RequestBody request: StartGameRequest): ResponseEntity<Any> {
        // DTO를 Persona 엔티티로 변환
        val persona = Persona(
            name = request.name,
            age = request.age,
            gender = request.gender,
            trait = request.trait
            // 초기 스탯, 스킬 등은 서비스 레이어나 다른 곳에서 설정할 수 있음
        )
        val session = gameSessionUseCase.createSession(persona)
        return ResponseEntity.ok(session.toResponse())
    }

    @PostMapping("/{sessionId}/turn")
    fun nextTurn(
        @PathVariable sessionId: String,
        @RequestBody request: UpdateTurnRequest
    ): ResponseEntity<Any> {
        val updatedSession = gameSessionUseCase.updateTurn(
            sessionId,
            request.log,
            request.choice
        ) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(updatedSession.toResponse())
    }

    @GetMapping("/{sessionId}")
    fun getSession(@PathVariable sessionId: String): ResponseEntity<Any> {
        val session = gameSessionUseCase.getSession(sessionId)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(session.toResponse())
    }
}
