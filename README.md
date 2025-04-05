AI와 함께 이어가는 텍스트 어드벤처!  
사용자의 선택에 따라 AI가 다음 스토리를 자동으로 생성해주는 게임입니다.

---

## 🛠 사용 기술

- **언어**: Kotlin
- **백엔드 프레임워크**: Spring Boot
- **빌드 도구**: Gradle
- **AI 서버 (예정)**: Python Flask + GPT
- **테스트 도구**: Postman

---

## 📁 프로젝트 구조

src/
├── domain/           # 스토리 데이터 모델 (Story.kt)
├── application/      # 핵심 비즈니스 로직 (StoryService.kt)
├── adapter/
│   ├── inbound/      # 컨트롤러 (StoryController.kt)
│   └── outbound/     # AI 요청 클라이언트 (AiClient.kt)

---

## 🚀 API 사용법

**요청 주소**  
`POST /api/story/next`

**요청 예시 (JSON)**

```json 형태로 임시 전송한 것
{
  "log": "나는 지금 어디에 있지?",
  "choice": "네가 어디 있는지 묻지 마라"
}
```

---

### ✅ 4단계: 구현 기능 & 향후 계획

```markdown```
---

## ✅ 지금까지 구현한 기능

- `Story` data class 설계
- `StoryController`, `Service`, `AiClient` 구성
- JSON 기반 API 설계 및 Postman 테스트 성공
- Spring 의존성 주입(DI), 에러 처리 등 학습 및 적용

---

## 🔮 향후 계획예정(수정가능성 높음)

- Flask AI 서버 연동 (GPT로 실제 스토리 생성)
- 사용자별 스토리 상태 관리 (Map 또는 DB)
- 선택지 리스트 구조화 (`choices: List<String>`)
- MySQL + JPA 연동
- GitHub + 블로그 기록 병행

---

## 📌 블로그 링크 (작성 예정)

https://velog.io/@kinemurayasuo/posts

---

## 🙌 Special Thanks

> "모르면 물어보자. 계속 묻고 끝까지 파고들면, 결국 만든다."

이 프로젝트는 스스로의 도전과 성장 기록입니다.

