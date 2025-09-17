package com.taewoo.aimudgame.domain

import com.taewoo.aimudgame.common.converter.ListConverter
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "game_sessions")
data class GameSession(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val sessionId: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    var turn: Int = 1,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    val persona: Persona,

    @Column(name = "log_history", columnDefinition = "TEXT")
    @Convert(converter = ListConverter.StringListConverter::class)
    val logHistory: MutableList<String> = mutableListOf(),

    @Column(name = "choice_history", columnDefinition = "TEXT")
    @Convert(converter = ListConverter.StringListConverter::class)
    val choiceHistory: MutableList<String> = mutableListOf(),

    @Column(name = "assigned_class")
    var assignedClass: String? = null,

    var ending: String? = null
)