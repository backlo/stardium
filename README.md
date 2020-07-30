# Stardium

## Stardium - ResourceServer

스타디움의 자원을 다루는 서버




## Stardium - AuthServcer

스타디움의 인증 담당 서버

* Server Port : 8081
* 매요청시 Request Header 값에 있는 Authorization 으로 사용자 인증 / 인가

#### 인증 구조

![spring security 인증](/Users/inkwon/IdeaProjects/WoowaTechCourse/step4/stardium-docs/images/spring security 구조.png)

#### 인증 순서

1. 요청으로 받은 사용자 정보는 **AuthenticationFilter** 에 들어와 파싱이 된다.
2. 인증을 하기 위해 사용자 정보를 **AuthenticationFilter** 는 **UsernamePasswordAuthenticationToken** 으로 만들어 값을 받는다.
3. 인증 절차를 밟게 하기 위해 **AuthenticationFilter** 는**AuthenticationManager(구현체: ProviderManager)** 에 인증 정보를 전달한다.
4. 실제 인증을 하기 위해 **AuthenticationManager** 는 가지고 있는 **UsernamePasswordAuthenticationToken** 을 **AuthenticationProviders** 에 전달한다.
   * 이때 **AuthenticationProviders** 는 여러개의 **AuthenticationProvider** 를 가지고 있고 인증을 다양하게 진행된다.
5. **AuthenticationProvider** 는 **UserDetailService** 를  사용해 사용자 인증 정보를 가지고 온다.
   * 여기서 **UserDetailService** 에 넘기는 객체는 **UserDetails** 를 넘긴다.
6. **UserDetailService** 는 DB에 있는 User 정보를 요청한다.
   * 아까 받았던 **UserDetails** 를 통해 User를 찾는다.
7. DB의 User 정보를 가지고 온다.
8. DB에서 가지고 온 User 정보를 요청했던 **AuthenticationProvider** 에 보낸다.
9. **AuthenticationProvider** 에서 User 정보를 사용자 입력정보와 비교해 인증을 한다.
   * 인증 성공시 인증 객체를 **SecurityContextHolder** 에 담은후 **AuthenticationSuccessHandler** 를 실행한다. (위 그림 10, 11)
   * 인증 실패시 **AuthenticationFailureHandler** 를 실행한다.

<br>

#### Security Filters

스프링 시큐어리티 필터에는 다음과 같이 동작한다.

![spring filters](/Users/inkwon/IdeaProjects/WoowaTechCourse/step4/stardium-docs/images/security filters.png)

1. **SecuirtyContextPersistenceFilter** : SecurityContextRepository에서 SecurityContext를 가져오거나 저장하는 역할
2. **LogoutFilter** : 설정된 로그아웃 URL로 오는 요청을 감시하며, 해당 유저를 로그아웃 처리하는 역할
3. **AuthenticationFilter(UsernamePasswordAuthenticationFilter)** : 아이디, 비밀번호 등 로그인 URL로 오는 요청을 감시하며, 유저 인증처리
4. **DefaultLoginPageGeneratingFilter** : 인증을 위한 로그인 URL을 감시하는 역할
5. **BasicAuthenticationFilter** : Http 기본 인증 헤더를 감시하여 처리하는 역할
6. **RequestChcheAwareFilter** : 로그인 성공 후, 원래 요청 정보를 재구성하는 역할
7. **SecurityContextHolderAwareRequestFilter** : HttpServletRequestWrapper를 상속한 SecurityContextHolderAwareRequestWrapper 클래스로 HttpServletRequest 정보를 감싸는 역할, SecurityContextHolderAwareRequestWrapper 클래스는 필터 체인상의 다음 필터들에게 부가정보를 제공
8. **AnonymousAuthenticationFilter** : 이 필터가 호출되는 시점까지 사용자 정보가 인증되지 않았다면 인증 토큰에 사용자가 익명 사용자로 나타내게 하는 역할
9. **SessionManagementFilter** : 인증된 사용자와 관련된 모든 세션을 추적하는 역할
10. **ExceptionTranslationFilter** : 보호된 요청을 처리하는 중에 발생할 수 있는 예외를 위임하거나 전달하는 역할
11. **FilterSecuirtyInterceptor** : AccessDecisionManager 로 권한 부여 처리를 위임함으로써 접근 제어 결정을 쉽게 해주는 역할
    * 즉 마지막 서블릿 필터로서 해당 요청의 수락 여부를 결정하는 필터

#### 이해가 안갔던 용어들

* **AbstractAuthenticationProcessingFilter** : 웹 기반 인증요청에서 사용되는 컴포넌트로 POST 폼 데이터를 포함하는 요청을 처리한다. 사용자 비밀번호를 다른 필터로 전달하기 위해서 Authentication 객체를 생성하고 일부 프로퍼티를 설정한다.
* **AuthenticationManager** : 인증 요청을 받고 Authentication을 채워준다.
* **AuthenticationProvider** : 실제 인증이 일어나고 만약 인증 성공시 Authentication 객체의 authenticated = true로 설정해준다.
* **Authentication** : 사용자 ID, 패스워드와 인증 요청 컨텍스트에 대한 정보를 가지고 있다. 인증 이후의 사용자 상세정보와 같은 UserDetails타입 오브젝트를 포함할 수도 있다.
* **UserDetails** : 이름, 이메일, 전화번호와 같은 사용자 프로파일 정보를 저장하기 위한 용도로 사용한다.
* **AuthenticationException** : 인증과관련된 예외는 이 클래스를 상속
* **AccessDecisionManager** : AccessDecisionVoter와 Vote 기반 접근 승인 방식을 제공
* 요청된 리소스에 대한 access 어트리뷰트 설정을 보터에게 전달하는 역할
  * 웹 URL 관련 access 어트리뷰트 설정 정보를 가지게 된다.
* **Voter** : 사용할 수 있는 정보를 사용해서 사용자의 리소스에 대한 접근 허가 여부를 판단
  * **ACCESS_GRANTED** : Voter가 리소스에 대한 접근 구너한을 허가하도록 권장한다.
  * **ACCESS_DENIED** : Voter가 리소스에 대한 접근 권한을 거부하도록 권장한다.
  * **ACCESS_ABSTAIN** : Voter는 리소스에 대한 접근권한 결정을 보류한다. 이 결정 보류는 다음과 같은 경우에 발생할 수 있다.
    * Voter가 접근 권한 판단에 필요한 결정적인 정보를 가지고 있지 않는 경우
    * Voter가 해당 타입의 요청에 대해 결정을 내릴 수 없는 경우


## Stardium - MediaServer

스타디움의 정적 파일들을 다루는 서버입니다.


## Stardium - SearchServer

스타디움의 검색 서비스를 다루는 서버입니다.


## Stardium - ChatServer

스타디움의 채팅을 다루는 서버입니다.