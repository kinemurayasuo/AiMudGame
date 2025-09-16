package com.taewoo.aimudgame.domain.repository

import com.taewoo.aimudgame.domain.Persona

interface PersonaPort {
    fun save (persona: Persona): Persona
    fun findById(id: Long): Persona?
}