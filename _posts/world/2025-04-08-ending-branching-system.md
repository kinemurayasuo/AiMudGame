🔚 엔딩 분기 시스템 설계 (Ending Branching System)

이 문서는 AI 스토리 게임에서 턴 300 도달 시 엔딩 분기 방식을 설계한 내용입니다.AI는 플레이어의 전체 행동 로그를 기반으로 결말을 생성하며, 선택 누적 결과에 따라 다양한 유형의 결말이 발생합니다.

🧩 엔딩 발생 시점

턴 300 도달 시 무조건 엔딩 생성

특별한 조건 (희귀 선택 반복, 유니크 Trait 반응 등)에 따라 조기 엔딩 가능

🔀 주요 엔딩 유형

유형

설명

예시

구원

희망적, 구조/성장 결말

“너는 세상을 바꿨고, 사람들은 널 기억했다.”

타락

부정적, 파괴/패배 결말

“그대는 마지막까지 욕망에 사로잡혔다.”

회귀

반복 또는 리셋 결말

“눈을 떴다. 또다시 그 장소였다.”

진실 도달

감춰진 세계의 본질 도달

“신은 없었다. 있었던 건 너의 질문뿐.”

소멸

존재 자체가 지워짐

“기억에서조차 너는 사라졌다.”

🧠 엔딩 결정 알고리즘 (Pseudocode)

function generateEnding(player):
result = analyzeChoices(player.logs, player.traits)

    if result.aggression > 0.8:
        return "타락"
    if result.empathy > 0.7:
        return "구원"
    if result.repetition > 0.6:
        return "회귀"
    if result.truthSeeking > 0.5:
        return "진실 도달"
    return "소멸"  // 기본값

📜 AI 엔딩 응답 예시

“…그리고 이 모든 이야기는, 그렇게 끝났다.

사람들은 네 이름을 기억했다. 아니, 기억하려 애썼다.
하지만 누구도 너처럼 선택하지는 못했을 것이다.”

🧱 상태 저장 예시 (JSON)

{
"turn": 300,
"endingType": "회귀",
"evaluation": {
"aggression": 0.4,
"empathy": 0.6,
"repetition": 0.75,
"truthSeeking": 0.2
}
}

✨ 확장 요소

다중 조건 엔딩 생성 (예: 회귀 + 진실 도달)

플레이어 클래스에 따라 결말 문장 차별화

AI가 엔딩 이후 후일담 생성 (에필로그 모드)

