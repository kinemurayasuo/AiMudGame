---
layout: post
title: "AI 인터랙티브 스토리 게임 개발기 - Kotlin + Spring Boot"
date: 2025-04-05
---

오늘은 나만의 AI 기반 인터랙티브 스토리 게임 프로젝트를 시작했다.

---

## 💡 프로젝트 개요

- **목표**: 사용자의 선택에 따라 스토리가 분기되고,  
  AI가 다음 스토리를 자동으로 이어주는 텍스트 기반 게임 만들기!
- **컨셉**: 고전 MUD 게임 + GPT 기반 AI 스토리텔링

---

## 🛠 사용 기술

- Kotlin + Spring Boot (백엔드)
- Gradle (빌드 도구)
- Postman (API 테스트)
- (예정) Flask + OpenAI API (AI 응답)
- (예정) MySQL + JPA (스토리 저장)

---

## 🔧 구현한 기능

- `Story` data class로 log/choice 구조 정의
- `StoryController`, `StoryService`, `AiClient` 로 API 흐름 구성
- Postman으로 JSON 요청 테스트 성공 ✅
- 의존성 주입, 에러 디버깅 직접 해결
- `@RequestBody` 활용해 JSON 자동 매핑 구현

---

## 🧪 Postman 테스트 예시

```http
POST /api/story/next
Content-Type: application/json

{
  "log": "나는 지금 어디에 있지?",
  "choice": "네가 어디 있는지 묻지 마라"
}

📌 느낀 점

진짜 처음엔 스프링 구조 자체도 어렵고,
AI랑 연결하는 게 너무 막막했는데
구조를 하나하나 쌓고,
직접 테스트해보니까 점점 손에 익는 느낌이다.

앞으로는 Flask로 GPT 연결하고,
선택지도 여러 개로 확장해서
AI와 진짜 게임처럼 대화할 수 있도록 발전시킬 예정이다.

⸻

📅 다음 목표
	•	Flask 기반 AI 서버 구축
	•	Spring ↔ Flask HTTP 연동
	•	유저별 스토리 저장 구조 설계
	•	GitHub + 블로그 통합 포트폴리오로 발전