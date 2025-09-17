package com.taewoo.aimudgame.common.converter

import com.taewoo.aimudgame.domain.Passive
import com.taewoo.aimudgame.domain.Skill
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ConverterTests {

    private lateinit var stringMapConverter: StringMapConverter
    private lateinit var stringListConverter: ListConverter.StringListConverter
    private lateinit var skillListConverter: ListConverter.SkillListConverter
    private lateinit var passiveListConverter: ListConverter.PassiveListConverter

    @BeforeEach
    fun setUp() {
        stringMapConverter = StringMapConverter()
        stringListConverter = ListConverter.StringListConverter()
        skillListConverter = ListConverter.SkillListConverter()
        passiveListConverter = ListConverter.PassiveListConverter()
    }

    @Test
    fun `StringMapConverter 테스트 - 정상적인 Map`() {
        // Given
        val originalMap = mapOf(
            "fire" to "strong",
            "water" to "weak",
            "earth" to "normal"
        )

        // When
        val dbData = stringMapConverter.convertToDatabaseColumn(originalMap)
        val restoredMap = stringMapConverter.convertToEntityAttribute(dbData)

        // Then
        assertNotNull(dbData)
        assertEquals(originalMap, restoredMap)
    }

    @Test
    fun `StringMapConverter 테스트 - 빈 Map`() {
        // Given
        val originalMap = emptyMap<String, String>()

        // When
        val dbData = stringMapConverter.convertToDatabaseColumn(originalMap)
        val restoredMap = stringMapConverter.convertToEntityAttribute(dbData)

        // Then
        assertNotNull(dbData)
        assertEquals(originalMap, restoredMap)
    }

    @Test
    fun `StringMapConverter 테스트 - null 처리`() {
        // Given
        val nullMap: Map<String, String>? = null

        // When
        val dbData = stringMapConverter.convertToDatabaseColumn(nullMap)
        val restoredMap = stringMapConverter.convertToEntityAttribute(null)

        // Then
        assertNotNull(dbData)
        assertEquals(emptyMap<String, String>(), restoredMap)
    }

    @Test
    fun `StringListConverter 테스트 - 정상적인 List`() {
        // Given
        val originalList = listOf("첫 번째 로그", "두 번째 로그", "세 번째 로그")

        // When
        val dbData = stringListConverter.convertToDatabaseColumn(originalList)
        val restoredList = stringListConverter.convertToEntityAttribute(dbData)

        // Then
        assertNotNull(dbData)
        assertEquals(originalList, restoredList)
    }

    @Test
    fun `StringListConverter 테스트 - 빈 List`() {
        // Given
        val originalList = emptyList<String>()

        // When
        val dbData = stringListConverter.convertToDatabaseColumn(originalList)
        val restoredList = stringListConverter.convertToEntityAttribute(dbData)

        // Then
        assertNotNull(dbData)
        assertEquals(originalList, restoredList)
    }

    @Test
    fun `StringListConverter 테스트 - null 처리`() {
        // Given
        val nullList: List<String>? = null

        // When
        val dbData = stringListConverter.convertToDatabaseColumn(nullList)
        val restoredList = stringListConverter.convertToEntityAttribute(null)

        // Then
        assertNotNull(dbData)
        assertEquals(emptyList<String>(), restoredList)
    }

    @Test
    fun `SkillListConverter 테스트 - 정상적인 Skill List`() {
        // Given
        val originalSkills = listOf(
            Skill("베기", "검으로 강하게 벤다", 20, 3),
            Skill("치유", "체력을 회복한다", 15, 5)
        )

        // When
        val dbData = skillListConverter.convertToDatabaseColumn(originalSkills)
        val restoredSkills = skillListConverter.convertToEntityAttribute(dbData)

        // Then
        assertNotNull(dbData)
        assertEquals(originalSkills.size, restoredSkills.size)
        assertEquals(originalSkills[0].name, restoredSkills[0].name)
        assertEquals(originalSkills[0].description, restoredSkills[0].description)
        assertEquals(originalSkills[1].name, restoredSkills[1].name)
    }

    @Test
    fun `SkillListConverter 테스트 - 빈 List`() {
        // Given
        val originalSkills = emptyList<Skill>()

        // When
        val dbData = skillListConverter.convertToDatabaseColumn(originalSkills)
        val restoredSkills = skillListConverter.convertToEntityAttribute(dbData)

        // Then
        assertNotNull(dbData)
        assertEquals(originalSkills, restoredSkills)
    }

    @Test
    fun `PassiveListConverter 테스트 - 정상적인 Passive List`() {
        // Given
        val originalPassives = listOf(
            Passive("어둠 속 행보", "밤에 회피율 +20%"),
            Passive("강인한 정신", "정신 공격에 저항")
        )

        // When
        val dbData = passiveListConverter.convertToDatabaseColumn(originalPassives)
        val restoredPassives = passiveListConverter.convertToEntityAttribute(dbData)

        // Then
        assertNotNull(dbData)
        assertEquals(originalPassives.size, restoredPassives.size)
        assertEquals(originalPassives[0].name, restoredPassives[0].name)
        assertEquals(originalPassives[0].description, restoredPassives[0].description)
        assertEquals(originalPassives[1].name, restoredPassives[1].name)
    }

    @Test
    fun `PassiveListConverter 테스트 - null 및 빈 문자열 처리`() {
        // Given & When & Then
        val fromNull = passiveListConverter.convertToEntityAttribute(null)
        val fromEmpty = passiveListConverter.convertToEntityAttribute("")

        assertEquals(emptyList<Passive>(), fromNull)
        assertEquals(emptyList<Passive>(), fromEmpty)
    }
}