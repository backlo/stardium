# 방 수정 화면 API 모음

### OverView
방 수정 화면에서 요청하는 API입니다.

방 수정 화면에서는 요청하는 API는 다음과 같습니다.

|name|type|
|------|---------------------------|
|방 수정 정보|EditClubInfoAPI|

## EditClubInfo API


### URL

```
http://localhost:8080/clubs/1
```

### Parameter

**Path Parameter**

```
/clubs/{id}
```

|key|value|
|------|------|
|id(방 아이디)|1|

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
PUT /clubs/1 HTTP/1.1
Host: localhost:8080
Authorization: "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.ey..."
...

{
    "title": "새 타이틀 입니다",
    "intro": "바뀐 인트로 입니다.",
    "city": "서울시",
    "section": "동대문구",
    "detail": "상세 정보들",
    "startTime": "2019-07-01T09:45",
    "endTime": "2019-07-01T12:45",
    "playerLimit": "4"
}
```

### Response

**Success**

```
HTTP/1.1 201 OK
Content-Type: application/json
...

{
    "id": 16,
    "title": "새 타이틀 입니다",
    "intro": "바뀐 인트로 입니다.",
    "address": {
        "city": "서울시",
        "section": "동대문구",
        "detail": "상세 정보들"
    },
    "playerLimit": 4,
    "startTime": "2019-07-01T09:45:00",
    "endTime": "2019-07-01T12:45:00",
    "master": {
        "email": "test1@test.com",
        "profileUrl": "https://stardium...",
        "nickname": "nickname1"
    },
    "joinPlayerCount": 1
}
```

**Error**

```
HTTP/1.1 403 OK
Content-Type: application/json
...

{
    "status": "FORBIDDEN",
    "errorEntity": {
        "timestamp": "2020-06-12 20:47:49",
        "code": 202,
        "message": "해당 플레이어가 개설한 방이 아닙니다"
    }
}
```


