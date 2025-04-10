package com.taewoo.aimudgame.domain

data class Skill(
    val name : String,
    val description : String,
    val power : Int = 0,
    val cost : Int = 0
)