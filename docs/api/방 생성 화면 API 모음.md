# 방 생성 화면 API 모음

### OverView
방 생성 화면에서 요청하는 API입니다.

방 생성 화면에서는 요청하는 API는 다음과 같습니다.

|name|type|
|------|---------------------------|
|방 생성|NewClubInfoAPI|

## NewClubInfoAPI API


### URL

```
http://localhost:8080/clubs
```

### Parameter

**Path Parameter**

* none

**Query String Parameter**

* none

**Data Params(application/json)**

|key|value|
|------|------|
|title|방 타이틀|
|intro|방 인트로|
|city|시|
|section|구|
|detail|상세 주소|
|startTime|게임 시작 시간|
|endTime|게임 종료 시간|
|playersLimit|플레이어 입장 제한 수|

### Request

```
POST /clubs HTTP/1.1
Host: localhost:8080
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ..."
...

{
    "title": "안녕",
    "intro": "반갑습니다",
    "city": "서울시",
    "section": "노원구",
    "detail": "멋진 ??",
    "startTime": "2021-10-01T09:45",
    "endTime": "2021-11-01T09:45",
    "playersLimit": "4"
}
```

### Response

**Success**

```
HTTP/1.1 201 OK
Content-Type: application/json
...

{
    "id": 13,
    "title": "안녕",
    "intro": "반갑습니다",
    "address": {
        "city": "서울시",
        "section": "노원구",
        "detail": "멋진 ??"
    },
    "playersLimit": 4,
    "startTime": "2021-10-01T09:45",
    "endTime": "2021-11-01T09:45",
    "master": {
        "email": "test1@test.com",
        "profileUrl": "https://stardium2020...",
        "nickname": "nickname1"
    },
    "joinPlayerCount": 1
}
```

**Error**

```
HTTP/1.1 400 OK
Content-Type: application/json
...

{
    "status": "BAD_REQUEST",
    "errorEntity": {
        "timestamp": "2020-06-11 18:38:25",
        "code": 200,
        "message": "현재 지원되지 않는 지역입니다."
    }
}
```