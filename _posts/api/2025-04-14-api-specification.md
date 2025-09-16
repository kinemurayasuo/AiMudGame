📘 AI 텍스트 어드벤처 API 명세서

기본 URL: http://localhost:8080/api/session

⸻

🔹 POST /start
•	설명: 새로운 게임 세션을 생성한다.
•	요청 바디 (JSON):

{
"name": "아렌",
"age": 22,
"gender": "남성",
"trait": "고요한 심연 같은 자"
}

	•	응답 예시:

{
"sessionId": "uuid",
"turn": 1,
"persona": { ... },
"logHistory": [],
"choiceHistory": []
}



⸻

🔹 POST /{sessionId}/next
•	설명: 턴을 진행하고 로그 + 선택지를 저장한다.
•	요청 바디 (TurnRequest DTO):

{
"log": "유적을 지나간다",
"choice": "왼쪽 문으로 간다"
}

	•	응답 예시:

{
"sessionId": "uuid",
"turn": 2,
"persona": { ... },
"logHistory": ["유적을 지나간다"],
"choiceHistory": ["왼쪽 문으로 간다"]
}



⸻

🔹 GET /{sessionId}
•	설명: 특정 세션의 현재 상태를 반환한다.
•	응답 예시:

{
"sessionId": "uuid",
"turn": 17,
"persona": { ... },
"assignedClass": null,
"ending": null,
"logHistory": [...],
"choiceHistory": [...]
}



⸻

📍 향후 확장 예정

🔸 POST /{sessionId}/reset
•	세션 초기화 기능 (예정)

🔸 GET /all
•	관리자용 전체 세션 목록 조회 (예정)

🔸 DELETE /{sessionId}
•	세션 삭제 기능 (예정)

⸻

✅ 참고
•	모든 요청/응답은 application/json 타입
•	유효성 검사를 위해 DTO 기반 처리 권장
•	오류 발생 시:
•	400 Bad Request (DTO 필드 누락 등)
•	404 Not Found (존재하지 않는 sessionId)