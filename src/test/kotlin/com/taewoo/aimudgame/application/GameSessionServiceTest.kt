package com.taewoo.aimudgame.application

import com.taewoo.aimudgame.domain.GameSession
import com.taewoo.aimudgame.domain.Persona
import com.taewoo.aimudgame.domain.repository.GameSessionPort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class GameSessionServiceTest {

    @Mock
    private lateinit var gameSessionPort: GameSessionPort

    private lateinit var gameSessionService: GameSessionService

    private lateinit var testPersona: Persona
    private lateinit var testSession: GameSession

    @BeforeEach
    fun setUp() {
        gameSessionService = GameSessionService(gameSessionPort)

        testPersona = Persona(
            id = 1L,
            name = "테스트 캐릭터",
            age = 25,
            gender = "남성",
            trait = "용감한"
        )

        testSession = GameSession(
            id = 1L,
            sessionId = "test-session-123",
            persona = testPersona,
            turn = 1
        )
    }

    @Test
    fun `세션 생성 테스트`() {
        // Given
        `when`(gameSessionPort.save(any(GameSession::class.java))).thenReturn(testSession)

        // When
        val result = gameSessionService.createSession(testPersona)

        // Then
        assertNotNull(result)
        assertEquals(testPersona, result.persona)
        assertEquals(1, result.turn)
        verify(gameSessionPort).save(any(GameSession::class.java))
    }

    @Test
    fun `세션 조회 테스트 - 성공`() {
        // Given
        val sessionId = "test-session-123"
        `when`(gameSessionPort.findById(sessionId)).thenReturn(testSession)

        // When
        val result = gameSessionService.getSession(sessionId)

        // Then
        assertNotNull(result)
        assertEquals(sessionId, result?.sessionId)
        assertEquals(testPersona, result?.persona)
        verify(gameSessionPort).findById(sessionId)
    }

    @Test
    fun `세션 조회 테스트 - 존재하지 않는 세션`() {
        // Given
        val sessionId = "non-existent-session"
        `when`(gameSessionPort.findById(sessionId)).thenReturn(null)

        // When
        val result = gameSessionService.getSession(sessionId)

        // Then
        assertNull(result)
        verify(gameSessionPort).findById(sessionId)
    }

    @Test
    fun `턴 업데이트 테스트 - 성공`() {
        // Given
        val sessionId = "test-session-123"
        val log = "플레이어가 마을을 탐험했습니다."
        val choice = "상점으로 이동"

        `when`(gameSessionPort.findById(sessionId)).thenReturn(testSession)
        `when`(gameSessionPort.save(testSession)).thenReturn(testSession)

        // When
        val result = gameSessionService.updateTurn(sessionId, log, choice)

        // Then
        assertNotNull(result)
        assertEquals(2, result?.turn) // 턴이 1 증가
        assertTrue(result?.logHistory?.contains(log) == true)
        assertTrue(result?.choiceHistory?.contains(choice) == true)
        verify(gameSessionPort).findById(sessionId)
        verify(gameSessionPort).save(testSession)
    }

    @Test
    fun `턴 업데이트 테스트 - 존재하지 않는 세션`() {
        // Given
        val sessionId = "non-existent-session"
        val log = "테스트 로그"
        val choice = "테스트 선택"

        `when`(gameSessionPort.findById(sessionId)).thenReturn(null)

        // When
        val result = gameSessionService.updateTurn(sessionId, log, choice)

        // Then
        assertNull(result)
        verify(gameSessionPort).findById(sessionId)
        verify(gameSessionPort, never()).save(any(GameSession::class.java))
    }

    @Test
    fun `턴 30 도달시 클래스 부여 테스트`() {
        // Given
        val sessionId = "test-session-123"
        val sessionWith29Turns = testSession.copy(turn = 29)
        val log = "전투에서 승리했습니다."
        val choice = "다음 지역으로 이동"

        `when`(gameSessionPort.findById(sessionId)).thenReturn(sessionWith29Turns)
        `when`(gameSessionPort.save(sessionWith29Turns)).thenReturn(sessionWith29Turns)

        // When
        val result = gameSessionService.updateTurn(sessionId, log, choice)

        // Then
        assertNotNull(result)
        assertEquals(30, result?.turn)
        assertNotNull(result?.assignedClass)
        assertEquals("그림자의 검", result?.assignedClass)
        assertTrue(result?.persona?.strength ?: 0 > 0) // 스탯이 증가했는지 확인
        verify(gameSessionPort).findById(sessionId)
        verify(gameSessionPort).save(sessionWith29Turns)
    }

    @Test
    fun `턴 300 도달시 엔딩 생성 테스트`() {
        // Given
        val sessionId = "test-session-123"
        val sessionWith299Turns = testSession.copy(turn = 299)
        val log = "최종 보스를 물리쳤습니다."
        val choice = "세계를 구원한다"

        `when`(gameSessionPort.findById(sessionId)).thenReturn(sessionWith299Turns)
        `when`(gameSessionPort.save(sessionWith299Turns)).thenReturn(sessionWith299Turns)

        // When
        val result = gameSessionService.updateTurn(sessionId, log, choice)

        // Then
        assertNotNull(result)
        assertEquals(300, result?.turn)
        assertNotNull(result?.ending)
        assertTrue(result?.ending?.contains("세계의 진실") == true)
        verify(gameSessionPort).findById(sessionId)
        verify(gameSessionPort).save(sessionWith299Turns)
    }
}