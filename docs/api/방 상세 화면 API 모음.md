# 방 상세 화면 API 모음

### OverView
방 상세 화면에서 요청하는 API입니다.

방 상세 화면에서는 요청하는 API는 다음과 같습니다.

|name|type|
|------|---------------------------|
|방 상세 정보|ClubInfoAPI|
|방 나가기|MatchOutAPI|

## ClubInfoAPI API

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

**Data Params**

* none

### Request

```
GET /clubs/1 HTTP/1.1
Host: localhost:8080
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ...
...
```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json
...

{
    "id": 3,
    "title": "굿잡",
    "intro": "고투 헬??",
    "address": {
        "city": "서울시",
        "section": "서대문구",
        "detail": "운동장"
    },
    "playersLimit": 6,
    "startTime": "2021-10-01T09:45:00",
    "endTime": "2021-11-01T09:45:00",
    "master": {
        "email": "test@test.com",
        "profileUrl": "https://stardium2020.s3.ap-northe...",
        "nickname": "nickname"
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
        "timestamp": "2020-06-12 20:03:34",
        "code": 201,
        "message": "해당 방을 찾을 수 없습니다."
    }
}
```

## MatchOut API

### URL

```
http://localhost:8080/matches/1/out
```

### Parameter

**Path Parameter**

```
/clubs/1/out
```

|key|value|
|------|------|
|id(방 아이디)|1|

**Query String Parameter**

* none

**Data Params**

* none

### Request

```
GET /clubs/1/out HTTP/1.1
Host: localhost:8080
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ...
...
```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json
...

{
    "flag": true
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
        "timestamp": "2020-06-22 20:52:29",
        "code": 402,
        "message": "해당 플레이어는 방에 없습니다."
    }
}
```