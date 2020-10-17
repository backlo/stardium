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
* DevOps
  * Gradle
  * Jenkins
  * Docker

## Stardium 구조

### 1차 - 기능 우선과 CI 구축 초점

<img src="https://github.com/backlo/stardium/blob/master/docs/images/Architecture1.png" width="60%" height="50%"/>

### 2차 - 기능 고도화 및 세분화 초점

<img src="https://github.com/backlo/stardium/blob/master/docs/images/Architecture2.png" width="60%" height="50%"/>

## Stardium 주요 내용

### 1차 - 팀 프로젝트

1. 기능을 중점으로 두고 프로젝트 제작
2. CI/CD 구축 경험
3. 현업 프로세스에 맞게 프로젝트 진행

### 2차 - 개인 프로젝트

1. 팀 프로젝트에서 부족했던 점이나 보완할 점을 고치기 위해 진행
2. 어플리케이션 타겟 변경으로 인한 REST API 로 변경
3. 모놀로틱 서버에서 멀티 모듈 서버로 변경

## Stardium에서 나의 역할

### 1차 - 팀 프로젝트

1. 플레이어가 원하는 소모임 방에 들어가는 비지니스 로직 구현
   1. Entity간 연관관계 매핑
2. 플레이어의 프로필 관리 로직 구현
   1. 정적 파일 AWS S3에 저장 기능 설계
3. 카카오 API를 사용해서 로그인 되게 하는 로직 구현
   1. 카카오 API 에게 로그인 요청하는 커넥터 구현
   2. 인증받은 사용자 정보를 Session 인증 방식으로 설계
4. Junit5를 이용해 서비스, 도메인 모델 테스트 진행
   1. 서비스의 신뢰성을 얻고자 테스트 커버리지 85%로 유지
   2. 인수 테스트는 따로 통합 테스트 모듈을 만들어 테스트 진행 
5. Semantic UI를 가지고 UI/UX 디자인 (Kakao map API, Daum Post API 등 사용)

### 2차 - 개인 프로젝트

1. 서비스 주 타겟이 스마트폰이라는 점을 착안하여 웹이 아닌 앱으로 변경 -> 프론트와 백을 분리(비동기식 처리 모델로 변경)
   1. [REST API](https://github.com/backlo/stardium/blob/master/docs/api/Stardium%20API.md)로 문서 작성
      * 자원과 행위를 최대한 잘 나타내도록 설계
      * Response Status Code를 의미있게 리턴
      * 화면별로 API 설계 -> 추후 자원에 맞게 정리 예정
2. 쿼리 튜닝
   1. QueryDSL를 사용
      * 사용한 이유
        * JPA는 엔티티를 반환받은 뒤 한번 더 DTO 객체로 변환해야하는 불편함을 느낌
        * 복잡한 쿼리를 날려야 할 경우 JPQL로 쿼리문을 직접 작성해야한다는 문제를 느낌 -> ORM의 방향과 다르다고 생각함
        * 컴파일 단계에서 오류를 찾을 수 있다는 장점 -> 명시적으로 자바를 사용해 코딩 할 수 있다는 점
      * JPQL로 되어있던 부분들을 QueryDSL로 변경
   2. 모든 연관관계 LAZY 로딩으로 설정 후 DB 성능 개선
      * 불필요한 쿼리가 안날라 가게 동적 쿼리 구현
      * N+1 문제 해결
3. Session 인증 방식  -> Spring Security + JWT 인증 방식으로 변경
   1. 사용자 정보를 암호화 하지않아 보안에 취약
   2. 접근 권한을 설정하지 않아 Admin 접근 제어에 불리
   3. Session방식은 내부 톰켓에 정보를 저장하는 구조로 되어있기 때문에 서버에 부담을 줌
   4. 필터에서 바로 인증 / 인가를 해주기 때문에 자원 낭비 절약
4. 단일 서버에서 다중 서버로 변경
   1. 단일 서버에 멀티 쓰레드 방식은 서버 부하 및 대기 시간이 길어진다는 단점이 있음
   2. 주기능과 부기능을 나눠 서버를 분리
   3. 이때 패키지로 구성 된 프로젝트에서 멀티 모듈로 변경해 서로 의존성을 낮추고 재사용이 가능하도록 분리
5. Docker를 사용하여 서버 스팩 로그를 기록
   1. 서버를 주기적으로 업데이트를 할 경우 관리가 불편
   2. 서버의 이력들을 docker-compose.yml로 코드화 하여 docker 컨테이너를 생성
   3. DockerFile로 서버 스팩을 정밀하게 명세화 할 예정