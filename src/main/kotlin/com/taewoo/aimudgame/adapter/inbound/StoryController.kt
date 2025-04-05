package com.taewoo.aimudgame.adapter.inbound

import com.taewoo.aimudgame.application.StoryService
import com.taewoo.aimudgame.domain.Story
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/story")
class StoryController(private val storyService: StoryService) {

    @PostMapping("/next")
    fun next(@RequestBody story: Story): String {
        return storyService.generateNextStory(story)
    }
}