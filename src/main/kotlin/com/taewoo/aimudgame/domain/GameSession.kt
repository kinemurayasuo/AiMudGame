package com.taewoo.aimudgame.domain

import com.taewoo.aimudgame.domain.Persona
import jakarta.persistence.*
import java.util.UUID

@Entity
data class GameSession(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
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
