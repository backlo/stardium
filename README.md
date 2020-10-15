# Stardium (2019.11 ~ 2020.05)

프로젝트 스타디움을 소개하는 md파일 입니다. 

## Stardium 소개

스타디움은 농구를 좋아하는 개인이 서로 원하는 시간과 장소에 모여 쉽게 농구를 할 수 있게 도와주는 서비스 입니다. 

사용자가 ``장소``와 ``시간``을 정해 게임방을 만들면 다른 사용자가 해당 게임방에 참여할 수 있습니다. 그리고 해당 방 인원이 가득 차면 게임이 ``자동으로 시작``하고 게임이 시작됐다는 알림이 가는 플로우를 가지고 있습니다.

## Stardium 기술 스택

* Language
  * **Java**
  * JavaScript
  * HTML/CSS
* Framework / Library
  * **Spring Boot**
  * Semantic UI
* Database
  * PostgreSQL / H2
  * Redis
  * Docker
* DevOps
  * Gradle
  * Jenkins

## Stardium 구조

### 1차

![](/Users/inkwon/IdeaProjects/WoowaTechCourse/step4/stardium/docs/images/Architecture1.png)

### 2차

![](/Users/inkwon/IdeaProjects/WoowaTechCourse/step4/stardium/docs/images/Architecture2.png)

## 내가 한 역할

### 1차 - 팀 프로젝트

  - 전반적인 웹 프론트 구현(Semantic UI, 카카오 맵 API 등 웹 UI/UX 디자인)
  - 사용자가 원하는 소모임 방에 들어가는 비지니스 로직 구현
  - S3를 사용해 정적 파일들을 저장 하도록 기능 구현
  - 로그인 로직 구현 (Session 방식으로 인증 인가 구현)
  - 서비스 신뢰성을 얻고자 테스트 커버리지 85% 유지

### 2차 - 개인 프로젝트

  - 서비스 타겟을 앱으로 변경함에 따라 서버를 비동기 통신 스팩으로 변경
  - Rest API 문서 작성 
  - JPQL을 사용했던 스팩을 QueryDSL로 변경
  - 모든 연관관계 LAZY 로딩으로 바꾸고 DB 성능 튜닝화 (불필요한 query 문 삭제 및 N+1 문제 해결)
  - Session 인증을 Spring Security + JWT 로 변경
  - 싱글 모듈에서 멀티 모듈로 변경하여 최대한 모듈끼리 의존하지 않고 재사용이 가능하도록 분리
  - 단일 서버를 다중 서버로 변경
  - Docker를 사용해 운용 서버 로그 기록

자세한 내용은 저장소 링크에 README.md 파일을 확인해주시면 감사하겠습니다.