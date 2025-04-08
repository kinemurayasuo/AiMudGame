# 🔄 턴 관리 구조 설계 (Turn Management System)

이 문서는 AI 기반 스토리 게임의 턴 진행 시스템을 설계한 내용입니다.  
총 300턴 구조 내에서 턴 수에 따라 이벤트 발생, AI 클래스 부여, 결말 유도 등의 흐름이 자동으로 이루어집니다.

---

## 🧩 턴(Turn)이란?

- 유저가 로그(log)와 선택(choice)을 보낸 후, AI가 응답을 생성하는 단위
- 한 턴마다 AI는 **이전까지의 대화 흐름 + 설정**을 바탕으로 다음 스토리를 생성

```plaintext
턴 = (사용자 입력 + 선택) → AI 응답 → 다음 턴으로 진입
```

---

## 🧭 턴 기반 주요 이벤트 흐름

| 턴 수 범위 | 주요 이벤트 / 역할 |
|------------|---------------------|
| 1 ~ 5턴 | 세계관 암시적 소개, 목표 제시, 분위기 조성 |
| 6 ~ 29턴 | 플레이어 성향 관찰 및 로그 축적 |
| **30턴** | 플레이어의 행동/성향 분석 → **직업/정체성 자동 생성** |
| 31 ~ 249턴 | 세계 변화, 사건 전개, AI 감정 반응 심화 |
| 250 ~ 299턴 | **결말 유도 구간**: 떡밥 회수, AI의 훈수 빈도 증가 |
| **300턴** | **무조건 엔딩 도달**, AI가 결말 생성 |

---

## 🧠 내부 상태 저장 구조 (예시)

```json
{
  "turn": 32,
  "persona": {
    "name": "아렌",
    "trait": "정의감이 강함"
  },
  "log_history": ["나는 지금 어디에 있지?", "..."],
  "choices": ["왼쪽 길로 간다", "노래를 부른다"],
  "classAssigned": true,
  "assignedClass": "방랑하는 기사"
}
```

---

## 🛠️ 턴 처리 로직 흐름 (백엔드 or AI 컨트롤러용)

```pseudocode
function processTurn(inputLog, inputChoice):
    turnCount += 1
    saveLogHistory(inputLog)
    saveChoiceHistory(inputChoice)

    if turnCount == 30 and classAssigned == false:
        assignedClass = generateClassFromPersona()
        classAssigned = true

    if turnCount >= 250:
        increaseNarratorHints()

    if turnCount >= 300:
        return generateFinalEnding()

    return generateNextStory()
```

---

## 🎯 엔딩 유도 규칙

- 턴 250부터는 **AI가 자주 감정적 반응**, 떡밥 회수 시도
- 300턴이 도달하면 반드시 엔딩 발생 (방식은 자유)
- 결말 유형 예시:
  - 구원 / 타락 / 소멸 / 루프 / 진실 도달 등

---

## 📝 확장 고려 요소

- 턴 수에 따라 선택지의 톤 변화 (초반은 일상, 후반은 중대)
- 턴마다 로그 길이 제한/증가 조정
- 엔딩 이후 “회고(리플레이)” 생성 가능성

---

> ※ 본 문서는 게임 흐름 제어용이며, AI 연동 시 `prompt`에 포함되거나  
>  Spring + Flask 시스템 간 상태 공유 시 구조화되어 사용될 수 있음.

