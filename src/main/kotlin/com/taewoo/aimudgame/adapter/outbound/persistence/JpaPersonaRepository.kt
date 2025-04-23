package com.taewoo.aimudgame.adapter.outbound.persistence

import com.taewoo.aimudgame.domain.Persona
import org.springframework.data.jpa.repository.JpaRepository

interface JpaPersonaRepository : JpaRepository<Persona, Long>