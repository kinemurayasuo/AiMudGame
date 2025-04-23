package com.yourproject.domain

import com.taewoo.aimudgame.domain.Persona
import jakarta.persistence.CascadeType
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.util.UUID

@Entity
data class GameSession(
    @Id
    val sessionId: String = UUID.randomUUID().toString(),
    var turn: Int = 1,
    @OneToOne(cascade = [CascadeType.ALL])
    val persona: Persona,
    @ElementCollection
    val logHistory: MutableList<String> = mutableListOf(),
    @ElementCollection
    val choiceHistory: MutableList<String> = mutableListOf(),

    var assignedClass: String? = null,
    var ending: String? = null
)
