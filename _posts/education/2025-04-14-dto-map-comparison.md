## 🧾 DTO vs Map<String, String> 비교: 요청 처리 방식 개선 사례

API 요청에서 `Map<String, String>`을 사용하는 기존 방식과,
DTO(Data Transfer Object) 클래스를 사용하는 방식의 차이를 아래와 같이 비교합니다.

---

### ❌ Before: Map<String, String> 사용

```kotlin
@PostMapping("/{sessionId}/next")
fun nextTurn(
    @PathVariable sessionId: String,
    @RequestBody request: Map<String, String>
): ResponseEntity<GameSession> {
    val log = request["log"] ?: ""
    val choice = request["choice"] ?: ""
    val updated = gameSession.updateTurn(sessionId, log, choice)
    return ResponseEntity.ok(updated)
}
```

#### 문제점
- 문자열 키를 직접 입력 → **오타 발생 가능**
- 값이 누락되어도 오류 없이 빈 문자열로 대체됨 → **검증 누락 위험**
- 코드의 의미가 직관적으로 드러나지 않음

---

### ✅ After: DTO 클래스 사용

```kotlin
data class TurnRequest(
    val log: String,
    val choice: String
)

@PostMapping("/{sessionId}/next")
fun nextTurn(
    @PathVariable sessionId: String,
    @RequestBody request: TurnRequest
): ResponseEntity<GameSession> {
    val updated = gameSession.updateTurn(sessionId, request.log, request.choice)
    return ResponseEntity.ok(updated)
}
```

#### 장점
- **타입 안전성 확보**: 오타나 누락 시 컴파일 타임에 오류 확인 가능
- **자동 검증 가능**: `@Valid` 등을 통해 유효성 검사 가능
- **직관적인 코드**: 어떤 필드가 들어오는지 명확히 보임
- **문서 자동화**: Swagger/OpenAPI에 구조가 자동 반영됨

---

### 📌 정리

| 항목 | Map 방식 | DTO 방식 |
|------|----------|----------|
| 타입 안전성 | ❌ 낮음 | ✅ 높음 |
| 코드 가독성 | ❌ 키 문자열이 많음 | ✅ 명확하고 구조화됨 |
| 실수 방지 | ❌ 오타 발생 가능 | ✅ 컴파일 타임 체크 |
| 문서화 | ❌ 어렵다 | ✅ Swagger에 자동 표시됨 |
| 추천 | 테스트 용도만 | ✅ 실제 서비스 적용에 적합 |

---

DTO 방식은 유지보수성과 안정성 측면에서 RESTful API 개발에 더욱 적합합니다.
지속적인 확장과 협업을 고려한다면 DTO 방식 도입을 권장합니다.

