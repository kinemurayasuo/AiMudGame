package com.taewoo.aimudgame.domain

// TODO: Entity로 승격 필요
data class Story(
    val turn: Int,
    val log: String,
    val playerChoice: String?, // 이 턴을 유발한 플레이어의 선택 (첫 턴은 null)
    val choices: List<String>,
)

