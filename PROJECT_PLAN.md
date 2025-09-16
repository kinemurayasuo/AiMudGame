# AiMudGame 프로젝트 분석 및 진행 계획

## 1. 프로젝트 분석

이 프로젝트는 AI를 활용한 텍스트 기반 어드벤처 게임으로, Kotlin과 Spring Boot를 사용하여 체계적으로 개발되고 있습니다. 특히, 헥사고날 아키텍처(Ports and Adapters)를 도입하여 비즈니스 로직과 외부 기술을 명확하게 분리한 점은 매우 훌륭합니다. `_posts` 폴더에 상세한 개발 일지와 설계 문서를 기록하고 있어, 프로젝트의 히스토리와 의도를 명확히 파악할 수 있습니다.

- **장점**:
    - **견고한 아키텍처**: 헥사고날 아키텍처를 적용하여 유연하고 확장 가능한 구조를 갖추었습니다.
    - **체계적인 문서화**: 개발 일지, API 명세, AI 설계 등 상세한 문서는 프로젝트의 유지보수성과 방향성을 명확하게 합니다.
    - **명확한 도메인 모델링**: `GameSession`, `Persona` 등 핵심 도메인이 JPA Entity로 잘 정의되어 있습니다.

- **개선 및 진행 필요**:
    - 코드 곳곳에 `TODO`로 표시된 미완성 기능들을 구체적으로 구현해야 합니다.
    - 실제 AI 서버와의 연동이 필요합니다.
    - 핵심 로직에 대한 테스트 코드가 부재하여 안정성 확보가 필요합니다.

## 2. 현재까지 진행 상황

- **[✔] 아키텍처 설계**: 헥사고날 아키텍처(Domain, Application, Adapter) 구조 확립.
- **[✔] 도메인 모델링**: `GameSession`, `Persona` Entity 및 `Story` 데이터 클래스 설계.
- **[✔] DB 연동**: JPA 및 MySQL을 연동하고, `List`, `Map` 타입 저장을 위한 `AttributeConverter` 구현.
- **[✔] 핵심 로직 구현**: `GameSessionService`에 게임 시작(`startGame`), 게임 이어하기(`continueGame`) 비즈니스 로직 구현.
- **[✔] API 기본 설계**: `GameSessionController`를 통한 API 엔드포인트(`POST /api/sessions/start`) 정의.
- **[✔] AI 연동 설계**: `AiClient` 인터페이스를 정의하고, `_posts`에 프롬프트 템플릿, 로어북 등 상세한 AI 연동 방안 설계.

## 3. 향후 진행 계획

앞으로 진행해야 할 작업들을 단계별 로드맵으로 정리했습니다.

### Phase 1: 핵심 기능 완성 (MVP)

가장 시급한 `TODO`들을 해결하여 게임의 핵심 루프를 실제로 동작하게 만드는 단계입니다.

-   **[ ] `GameSessionController` 구현 완료**
    -   [ ] `StartGameRequest`, `ContinueGameRequest` 등 Request DTO 정의.
    -   [ ] `GameSessionResponse` 등 Response DTO 정의.
    -   [ ] `startGame`, `continueGame` API 로직을 DTO와 연결하여 완성.
    -   [ ] 임시 `StoryController` 제거.

-   **[ ] `AiClient` 실제 연동 구현**
    -   [ ] `RestTemplate` 또는 `WebClient`를 사용하여 실제 Flask AI 서버와 HTTP 통신 구현.
    -   [ ] `application.yml`에 있는 `ai.server.url`을 사용하여 요청.
    -   [ ] AI 서버와 주고받을 `AiRequest`, `AiResponse` DTO 정의 및 적용.

-   **[ ] `Story` 도메인 모델 개선**
    -   [ ] `Story`를 `data class`에서 `@Entity`로 변경.
    -   [ ] `GameSession`과 `Story`를 1:N 양방향 관계로 설정 (`@OneToMany`, `@ManyToOne`).
    -   [ ] `GameSession.storyLogs`가 `Story` 엔티티의 리스트를 참조하도록 변경.

### Phase 2: 코드 안정성 및 테스트

-   **[ ] 단위 테스트(Unit Test) 작성**
    -   [ ] `GameSessionService`의 비즈니스 로직에 대한 단위 테스트 작성 (Mockito 등 활용).
-   **[ ] 통합 테스트(Integration Test) 작성**
    -   [ ] `GameSessionController`의 API 동작에 대한 통합 테스트 작성 (`@SpringBootTest` 활용).

### Phase 3: 기능 확장

-   **[ ] 사용자(User) 시스템 도입**
    -   [ ] `User` Entity 설계 (ID, Password 등).
    -   [ ] Spring Security를 이용한 회원가입 및 로그인/인증 기능 구현.
    -   [ ] `User`와 `GameSession`을 1:N 관계로 설정 (한 명의 유저가 여러 게임 세션을 가질 수 있도록).

-   **[ ] 페르소나 관리 기능**
    -   [ ] 페르소나 생성, 조회, 목록 보기에 대한 `PersonaService` 및 `PersonaController` 구현.

### Phase 4: AI 및 프롬프트 고도화

-   **[ ] 프롬프트 템플릿 시스템 구현**
    -   [ ] `_posts`에 설계된 프롬프트 템플릿(`world/2025-04-08-ai-storygame-prompt-template.md`)을 코드로 구현.
    -   [ ] `GameSessionService`에서 프롬프트를 동적으로 조립하는 로직 개선.

-   **[ ] 로어북(Lorebook) 시스템 도입**
    -   [ ] 게임 세계관 키워드 및 설정을 저장하고, AI 프롬프트에 동적으로 주입하는 기능 구현.

### Phase 5: 배포 및 프론트엔드

-   **[ ] 배포 (Deployment)**
    -   [ ] Docker를 이용하여 Spring Boot 애플리케이션 및 MySQL 컨테이너화.
    -   [ ] `docker-compose`를 사용하여 배포 환경 구성.
-   **[ ] 프론트엔드 구현**
    -   [ ] 간단한 웹 UI (React, Vue 등) 또는 CLI 클라이언트 제작하여 실제 게임 플레이 경험 제공.
