package com.taewoo.aimudgame.application

import com.taewoo.aimudgame.adapter.outbound.AiClient
import com.taewoo.aimudgame.domain.Story
import org.springframework.stereotype.Service

@Service
class StoryService(private val aiClient: AiClient) {
    fun generateNextStory(story : Story): String {
        return aiClient.requestNextStory(story)
    }

}