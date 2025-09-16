package com.taewoo.aimudgame.adapter.inbound.dto

import com.taewoo.aimudgame.domain.GameSession
import com.taewoo.aimudgame.domain.Persona

fun Persona.toResponse(): PersonaResponse {
    return PersonaResponse(
        id = this.id,
        name = this.name,
        age = this.age,
        gender = this.gender,
        trait = this.trait,
        level = this.level,
        hp = this.hp,
        mp = this.mp,
        strength = this.strength,
        magic = this.magic,
        endurance = this.endurance,
        agility = this.agility,
        luck = this.luck,
        skills = this.skills.map { it.name },
        passives = this.passives.map { it.name }
    )
}

fun GameSession.toResponse(): GameSessionResponse {
    return GameSessionResponse(
        sessionId = this.sessionId,
        turn = this.turn,
        persona = this.persona.toResponse(),
        logHistory = this.logHistory,
        choiceHistory = this.choiceHistory,
        assignedClass = this.assignedClass,
        ending = this.ending,
    )
}
