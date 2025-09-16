# 트러블슈팅 정리 - 2025.04.23

## 🧨 문제 1: Hibernate Map 매핑 오류
- **에러**: `Could not determine recommended JdbcType for Java type 'Map<String, String>'`
- **원인**: JPA는 기본적으로 Map을 지원하지 않음
- **해결**: `@Convert(converter = StringMapConverter::class)` 추가 + `StringMapConverter` 생성

```kotlin
@Convert(converter = StringMapConverter::class)
var resistances: Map<String, String> = mapOf()
```

## 🧨 문제 2: List 타입 매핑 오류
- **에러**: List 타입도 Map과 마찬가지로 직접 매핑 불가
- **해결**: 커스텀 `ListConverter` 작성 후 각 타입에 적용

```kotlin
@Convert(converter = ListConverter.SkillListConverter::class)
var skills: List<Skill> = listOf()
```

## 🧨 문제 3: `@Entity`와 `@Embeddable` 혼용
- **에러**: `Invalid class annotated both '@Entity' and '@Embeddable'`
- **원인**: JPA는 둘 중 하나만 허용
- **해결**: `@Embeddable` 제거

## 🧨 문제 4: JPA 엔티티 인식 실패
- **에러**: `Not a managed type: com.yourproject.domain.GameSession`
- **원인**: 잘못된 패키지 이름(`com.yourproject`) 또는 해당 클래스에 `@Entity` 누락
- **해결**:
    1. `@Entity` 확인
    2. `@EnableJpaRepositories`의 `basePackages`가 올바르게 설정되었는지 확인

## ✅ 기타 팁
- `typealias`를 활용해 가독성 및 반복 제거
- `@ElementCollection`은 간단한 List<String>, Map<String, String> 저장에만 적합

---

이 문서는 JPA 설정 중 발생할 수 있는 대표적인 오류 상황과 그 해결법을 빠르게 회고할 수 있도록 작성된 트러블슈팅 문서입니다.

