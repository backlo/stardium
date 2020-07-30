# 홈 화면 API 모음

### OverView
홈 화면에서 요청하는 API입니다.

홈 화면에서는 요청하는 API는 다음과 같습니다.

|name|type|
|------|---------------------------|
|생성되어 있는 방 모음|ClubListAPI|
|플레이어가 참여한 방 모음|MatchClubListAPI|
|플레이어 참여|MatchInAPI|
|정렬 할 구(Section) 모음|GetSectionsAPI|
|정렬 된 구|SortSectionAPI|
|검색|SearchAPI|

## ClubListAPI

### URL

```
http://localhost:8080/clubs?page=1&size=5&direction=ASC&properties=startTime
```

### Parameter

**Path Parameter**

* none

**Query String Parameter**

```
/clubs?page=1&size=5&direction=ASC&properties=startTime
```

|key|value|
|------|------|
|page(쪽)|1|
|size(개수)|5|
|direction(차순)|ASC|
|properties(정렬할 필드 기준)|startTime|

**Data Params**

* none

### Request

```
GET /clubs?page=1&size=5&direction=DESC&properties=createTime HTTP/1.1
Host: localhost:8080
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUB0ZXN0LmNvb...
```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json
...

{
    "content": [
        {
            "id": 19,
            "title": "타이틀 훗",
            "intro": "인트로 이겨보시지",
            "address": {
                "city": "서울시",
                "section": "서대문구",
                "detail": "블라블라"
            },
            "playersLimit": 6,
            "startTime": "2020-07-01T09:45",
            "endTime": "2020-07-01T12:45",
            "master": {
                "email": "test5@test.com",
                "profileUrl": "https://stardium2020.s3....",
                "nickname": "nickname5"
            },
            "joinPlayerCount": 1
        },
        {
            "id": 4,
            "title": "타이틀 입니다4",
            "intro": "인트로 입니다.4",
            "address": {
                "city": "서울시",
                "section": "동대문구2",
                "detail": "상세 정보들2"
            },
            "playersLimit": 6,
            "startTime": "2021-07-01T09:45",
            "endTime": "2021-07-01T12:45",
            "master": {
                "email": "test@test.com",
                "profileUrl": "https://stardium2020....",
                "nickname": "nickname"
            },
            "joinPlayerCount": 2
        },
        ...
        {
            "id": 3,
            "title": "타이틀 입니다3",
            "intro": "인트로 입니다.3",
            "address": {
                "city": "서울시",
                "section": "동대문구2",
                "detail": "상세 정보들2"
            },
            "playersLimit": 6,
            "startTime": "2021-07-01T09:45",
            "endTime": "2021-07-01T12:45",
            "master": {
                "email": "test@test.com",
                "profileUrl": "https://stardium2020.s3....",
                "nickname": "nickname"
            },
            "joinPlayerCount": 2
        }
    ],
    "totalPages": 4,
    "elementSize": 5,
    "pageNumber": 0,
    "sortEnable": true
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
        "timestamp": "2020-06-10 16:07:41",
        "code": 100,
        "message": "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted."
    }
}
```

## MatchClubListAPI

### URL

```
http://localhost:8080/matches?page=3&size=5&direction=DESC&properties=createTime
```

### Parameter

**Path Parameter**

* none

**Query String Parameter**

```
/matches?page=3&size=5&direction=DESC&properties=createTime
```

|key|value|
|------|------|
|page(쪽)|1|
|size(개수)|5|
|direction(차순)|DESC|
|properties(정렬할 필드 기준)|createTime|

**Data Params**

* none

### Request

```
GET /matches?page=3&size=5&direction=DESC&properties=createTime HTTP/1.1
Host: localhost:8080
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUB0ZXN0LmNvb...
```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json
...

{
    "content": [
        {
            "id": 4,
            "title": "타이틀 입니다4",
            "intro": "인트로 입니다.4",
            "address": {
                "city": "서울시",
                "section": "동대문구2",
                "detail": "상세 정보들2"
            },
            "playersLimit": 6,
            "startTime": "2021-07-01T09:45:00",
            "endTime": "2021-07-01T12:45:00",
            "master": {
                "email": "test@test.com",
                "profileUrl": "https://stardium2020.s3.a...",
                "nickname": "nickname"
            },
            "joinPlayerCount": 2
        },
        ...
        {
            "id": 1,
            "title": "새 타이틀 입니다",
            "intro": "바뀐 인트로 입니다.",
            "address": {
                "city": "서울시",
                "section": "동대문구",
                "detail": "상세 정보들"
            },
            "playersLimit": 4,
            "startTime": "2019-07-01T09:45:00",
            "endTime": "2019-07-01T12:45:00",
            "master": {
                "email": "test@test.com",
                "profileUrl": "https://stardium2020.s3.ap...",
                "nickname": "nickname"
            },
            "joinPlayerCount": 3
        }
    ],
    "totalPages": 3,
    "elementSize": 5,
    "pageNumber": 2,
    "sortEnable": true
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
        "timestamp": "2020-06-10 16:07:41",
        "code": 100,
        "message": "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted."
    }
}
```

## MatchInAPI

### URL

```
http://localhost:8080/matches/1/in
```

### Parameter

**Path Parameter**

```
/matches/{id}/in
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
GET /matches/1/in HTTP/1.1
Host: localhost:8080
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzd...
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
HTTP/1.1 400 OK
Content-Type: application/json
...

{
    "status": "BAD_REQUEST",
    "errorEntity": {
        "timestamp": "2020-06-22 20:05:37",
        "code": 400,
        "message": "이미 방에 입장을 했습니다."
    }
}
```

## GetSectionsAPI

### URL

```
http://localhost:8080/clubs/sections
```

### Parameter

**Path Parameter**

* none

**Query String Parameter**

* none

**Data Params**

* none

### Request

```
GET /clubs/sections HTTP/1.1
Host: localhost:8080
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzd...
```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json
...

{
    "sections": [
        "강남구",
        "강동구",
        "강북구",
        ...
        "중랑구"
    ]
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
        "timestamp": "2020-06-26 00:02:09",
        "code": 100,
        "message": "Invalid Jwt Token : Can't Accessed"
    }
}
```

## SortSectionAPI

### URL

```
http://localhost:8080/clubs/sort?page=1&size=5&direction=ASC&properties=startTime&section=동대문구 
```

### Parameter

**Path Parameter**

* none

**Query String Parameter**

```
/clubs/sort?page=1&size=5&direction=ASC&properties=startTime&section=동대문구 
```

|key|value|
|------|------|
|page(쪽)|1|
|size(개수)|5|
|direction(차순)|ASC|
|properties(정렬할 필드 기준)|startTime|
|section(구역)|동대문구|

**Data Params**

* none

### Request

```
GET /clubs/sort?page=1&size=5&direction=ASC&properties=startTime&section=동대문구  HTTP/1.1
Host: localhost:8080
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzd...
```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json
...

{
    "content": [
        {
            "id": 1,
            "title": "새 타이틀 입니다",
            "intro": "바뀐 인트로 입니다.",
            "address": {
                "city": "서울시",
                "section": "동대문구",
                "detail": "상세 정보들"
            },
            "playersLimit": 4,
            "startTime": "2019-07-01T09:45",
            "endTime": "2019-07-01T12:45",
            "master": {
                "email": "test@test.com",
                "profileUrl": "https://stardium2020.s...",
                "nickname": "nickname"
            },
            "joinPlayerCount": 3
        },
        {
            "id": 17,
            "title": "새 타이틀",
            "intro": "바뀐 인트로.",
            "address": {
                "city": "서울시",
                "section": "동대문구",
                "detail": "상세 정보들!!"
            },
            "playersLimit": 6,
            "startTime": "2019-07-01T09:45",
            "endTime": "2019-07-01T12:45",
            "master": {
                "email": "test5@test.com",
                "profileUrl": "https://stardium2020.s3....",
                "nickname": "nickname5"
            },
            "joinPlayerCount": 1
        }
    ],
    "totalPages": 1,
    "elementSize": 5,
    "pageNumber": 0,
    "sortEnable": true
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
        "timestamp": "2020-06-26 00:05:34",
        "code": 201,
        "message": "허용 되지 않는 구 입니다."
    }
}
```

## SearchAPI

### URL

```
http://localhost:8083/search?keyword=바뀐
```

### Parameter

**Path Parameter**

* none

**Query String Parameter**

```
/search?keyword=바뀐
```

|key|value|
|------|------|
|keyword(찾는 단어)|바뀐|


**Data Params**

* none

### Request

```
GET /search/바뀐 HTTP/1.1
Host: localhost:8083
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJ...
```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json
...

{
    "searchClubs": [
        {
            "id": 1,
            "title": "새 타이틀 입니다",
            "intro": "바뀐 인트로 입니다.",
            "address": {
                "city": "서울시",
                "section": "동대문구",
                "detail": "상세 정보들"
            },
            "playersLimit": 4,
            "startTime": "2019-07-01T09:45",
            "endTime": "2019-07-01T12:45",
            "master": {
                "email": "test@test.com",
                "profileUrl": "https://stardium2020.s3....",
                "nickname": "nickname"
            },
            "joinPlayerCount": 3
        },
        {
            "id": 17,
            "title": "새 타이틀",
            "intro": "바뀐 인트로.",
            "address": {
                "city": "서울시",
                "section": "동대문구",
                "detail": "상세 정보들!!"
            },
            "playersLimit": 6,
            "startTime": "2019-07-01T09:45",
            "endTime": "2019-07-01T12:45",
            "master": {
                "email": "test5@test.com",
                "profileUrl": "https://stardium2020.s3.a...",
                "nickname": "nickname5"
            },
            "joinPlayerCount": 1
        }
    ]
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
        "timestamp": "2020-06-26 01:42:51",
        "code": 100,
        "message": "Unsigned Claims JWTs are not supported."
    }
}
```