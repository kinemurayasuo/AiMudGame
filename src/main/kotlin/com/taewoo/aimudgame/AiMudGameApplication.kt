package com.taewoo.aimudgame

import jakarta.persistence.Entity
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("com.taewoo.aimudgame.domain")
class AiMudGameApplication

fun main(args: Array<String>) {
    runApplication<AiMudGameApplication>(*args)
}
