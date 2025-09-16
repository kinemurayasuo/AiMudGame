package com.taewoo.aimudgame.adapter.outbound.persistence

import com.taewoo.aimudgame.domain.Persona
import com.taewoo.aimudgame.domain.repository.PersonaPort
import org.springframework.stereotype.Repository

@Repository
class PersonaPersistenceAdapter (
    private val jpaRepository: JpaPersonaRepository
) : PersonaPort {
    override fun save(persona: Persona): Persona {
        return jpaRepository.save(persona)
    }
    override fun findById(id: Long): Persona? {
        return jpaRepository.findById(id).orElse(null)
    }
}