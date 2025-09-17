package com.taewoo.aimudgame.adapter.inbound.dto

import com.taewoo.aimudgame.domain.GameSession
import com.taewoo.aimudgame.domain.Passive
import com.taewoo.aimudgame.domain.Persona
import com.taewoo.aimudgame.domain.Skill
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MapperTest {

    private lateinit var testPersona: Persona
    private lateinit var testGameSession: GameSession

    @BeforeEach
    fun setUp() {
        testPersona = Persona(
            id = 1L,
            name = "테스트 캐릭터",
            age = 25,
            gender = "남성",
            trait = "용감한",
            level = 5,
            hp = 100,
            mp = 50,
            strength = 15,
            magic = 10,
            endurance = 12,
            agility = 8,
            luck = 6,
            skills = listOf(
                Skill("베기", "검으로 강하게 벤다", 20, 3),
                Skill("치유", "체력을 회복한다", 15, 5)
            ),
            passives = listOf(
                Passive("어둠 속 행보", "밤에 회피율 +20%"),
                Passive("강인한 정신", "정신 공격에 저항")
            )
        )

        testGameSession = GameSession(
            id = 1L,
            sessionId = "test-session-123",
            turn = 10,
            persona = testPersona,
            logHistory = mutableListOf("첫 번째 로그", "두 번째 로그"),
            choiceHistory = mutableListOf("첫 번째 선택", "두 번째 선택"),
            assignedClass = "전사",
            ending = null
        )
    }

    @Test
    fun `Persona toResponse 매핑 테스트`() {
        // When
        val response = testPersona.toResponse()

        // Then
        assertEquals(testPersona.id, response.id)
        assertEquals(testPersona.name, response.name)
        assertEquals(testPersona.age, response.age)
        assertEquals(testPersona.gender, response.gender)
        assertEquals(testPersona.trait, response.trait)
        assertEquals(testPersona.level, response.level)
        assertEquals(testPersona.hp, response.hp)
        assertEquals(testPersona.mp, response.mp)
        assertEquals(testPersona.strength, response.strength)
        assertEquals(testPersona.magic, response.magic)
        assertEquals(testPersona.endurance, response.endurance)
        assertEquals(testPersona.agility, response.agility)
        assertEquals(testPersona.luck, response.luck)

        // 스킬 이름 매핑 확인
        assertEquals(2, response.skills.size)
        assertTrue(response.skills.contains("베기"))
        assertTrue(response.skills.contains("치유"))

        // 패시브 이름 매핑 확인
        assertEquals(2, response.passives.size)
        assertTrue(response.passives.contains("어둠 속 행보"))
        assertTrue(response.passives.contains("강인한 정신"))
    }

    @Test
    fun `GameSession toResponse 매핑 테스트`() {
        // When
        val response = testGameSession.toResponse()

        // Then
        assertEquals(testGameSession.sessionId, response.sessionId)
        assertEquals(testGameSession.turn, response.turn)
        assertEquals(testGameSession.logHistory, response.logHistory)
        assertEquals(testGameSession.choiceHistory, response.choiceHistory)
        assertEquals(testGameSession.assignedClass, response.assignedClass)
        assertEquals(testGameSession.ending, response.ending)

        // 중첩된 Persona 매핑 확인
        assertEquals(testPersona.id, response.persona.id)
        assertEquals(testPersona.name, response.persona.name)
        assertEquals(testPersona.level, response.persona.level)
    }

    @Test
    fun `빈 스킬과 패시브를 가진 Persona 매핑 테스트`() {
        // Given
        val personaWithEmptyLists = testPersona.copy(
            skills = emptyList(),
            passives = emptyList()
        )

        // When
        val response = personaWithEmptyLists.toResponse()

        // Then
        assertTrue(response.skills.isEmpty())
        assertTrue(response.passives.isEmpty())
    }

    @Test
    fun `null 엔딩을 가진 GameSession 매핑 테스트`() {
        // Given
        val sessionWithNullEnding = testGameSession.copy(ending = null)

        // When
        val response = sessionWithNullEnding.toResponse()

        // Then
        assertNull(response.ending)
        assertEquals(testGameSession.sessionId, response.sessionId)
        assertEquals(testGameSession.turn, response.turn)
    }

    @Test
    fun `빈 히스토리를 가진 GameSession 매핑 테스트`() {
        // Given
        val sessionWithEmptyHistory = testGameSession.copy(
            logHistory = mutableListOf(),
            choiceHistory = mutableListOf()
        )

        // When
        val response = sessionWithEmptyHistory.toResponse()

        // Then
        assertTrue(response.logHistory.isEmpty())
        assertTrue(response.choiceHistory.isEmpty())
        assertEquals(testGameSession.sessionId, response.sessionId)
    }
}