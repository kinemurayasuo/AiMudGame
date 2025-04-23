package com.taewoo.aimudgame.application

import com.taewoo.aimudgame.domain.Passive
import com.taewoo.aimudgame.domain.Persona
import com.taewoo.aimudgame.domain.Skill
import com.taewoo.aimudgame.domain.repository.GameSessionPort
import com.taewoo.aimudgame.domain.GameSession
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GameSessionService(private val gameSessionPort: GameSessionPort
) : GameSessionUseCase
 {

    // 세션 임시 메모리 저장 mutableMapOf
    private val sessions = mutableMapOf<String, GameSession>()

    // 세션 생성
    override fun createSession(persona: Persona): GameSession {
        val sessionId = UUID.randomUUID().toString()
        val session = GameSession(sessionId = sessionId, persona = persona)
        return gameSessionPort.save(session)
    }

    // 세션 상태 조회
    override fun getSession(sessionId: String): GameSession? {
        return gameSessionPort.findById(sessionId)
    }

    // 턴 진행
    override fun updateTurn(sessionId: String, log: String, choice: String): GameSession? {
        val session = sessions[sessionId] ?: return null
        session.turn += 1
        session.logHistory.add(log)
        session.choiceHistory.add(choice)

        // TODO: 턴 30 시점에서 클래스/스탯 자동 부여 로직 추가
        if (session.turn == 30) {
            session.assignedClass = "그림자의 검" // 예시
            session.persona.strength += 5
            session.persona.skills = listOf(Skill("베기", "검으로 강하게 벤다", 20, 3))
            session.persona.passives = listOf(Passive("어둠 속 행보", "밤에 회피율 +20%"))
        }

        // TODO: 턴 300 도달 시 엔딩 생성
        if (session.turn >= 300) {
            session.ending = "당신은 세계의 진실에 도달했다..."
        }

        return gameSessionPort.save(session)
    }

}