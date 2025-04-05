package com.taewoo.aimudgame.adapter.outbound

import com.taewoo.aimudgame.domain.Story
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class AiClient {
    private val restTemplate = RestTemplate()

    /*fun requestNextStory(story: Story): String {
        val header = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val entity = HttpEntity(story, header)

        val response = restTemplate.postForEntity(
            "플라스크 서버 주소",
            entity,
            String::class.java
        )
        return response.body ?: "AI 응답 없음"
    }*/

    fun requestNextStory(story: Story): String {
        return "AI 응답 테스트 : [${story.log}] -> 선택: ${story.choice}"
    }
}