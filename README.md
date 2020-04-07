프로젝트 소개
 기존 스포츠 모임 혹은 동아리나 동호회등 즐기려면 대부분 사람들이 동호회 카페나 블로그등 직접 글을 올려 사람을 모집했습니다. 
 이러한 방법은 사람들이 약속을 잡고 안나가거나,  당일 취소 등 어뷰징 요소가 많았고, 
 예약제로 운영되지 않기 때문에 두 모임이 동시간대에  동일한 장소를 사용하는 경우, 
 하나의 무리만 사용하는 불편함을 겪었습니다. 이러한 문제를 해결하기 위해 서로 원하는 시간과 장소에 모여 쉽게 여가 
 활동을 할 수 있게  도와주는 서비스를 만들고 싶었습니다. 따라서 첫 종목을 농구로 잡았으며 개인이 서로 원하는 
 시간과 장소에 모여 쉽게 농구를 할 수 있게 도와주는 서비스를 만들었습니다. 또한 기획부터 시작한 프로젝트라 설계에 
 많이 투자했습니다.

본인이 수행한 역할
* BE
 1. 사용자(Player) 
    * 사용자 정보(Create Time, Update Time, Profile Path, ... ) CRUD 구현 
    * 사용자 도메인과 방 정보 도메인 연결 기능 구현 - 각 테이블간의 연관관계 설정
 2. 미디어(Media)
    * 미디어 파일(프롤필 사진) 저장 후 Path Url 반환 기능 및구현
    * 스토리지 구조 - DAS 형태로 설계
 3. 테스트
    * Controller : 통합 테스트로 진행
    * Service : Mock 테스트 진행
    * Entity, Value : Jupiter, assertJ로 진행

* FE (https://github.com/billionaire-boys/stardium/wiki)
    * 전체 UI/UX 설계
    * 서버 사이드 랜더링으로 진행
    * Daum Post Api, Kakao Map Api를 이용해 지도 및 주소 기능 구현

* Dev Ops
    * Git Actions을 통해 CI 구축
    * DNS  연결하기 위해 Nginx 설정

* 추가로 구현한 기능 (진행중)
    * 미디어 파일 저장하는 방법을 AWS S3를 이용하는 구조 변경
    * 방장 위임 기능 추가
    * 모든 도메인을 비동기로 통신 구조 변경 및 패키지 구조 변경
    * 카카오 api를 사용해 사용자 인증/인가 구현
    * Nginx를 설정하여 무중단 배포 구축 (예정)

* 성과/ 느낀점
   * 지속적인 통합이 무엇인지 알 수 있었습니다.
   * 기획부터 배포까지, 하나의 서비스를 런칭할 수 있었던 좋은 계기였던것 같습니다.
        * 주도적으로 서비스를 개선한 프로젝트였습니다.