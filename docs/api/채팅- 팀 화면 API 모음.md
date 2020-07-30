# 채팅/ 팀 화면 API 모음

### OverView
채팅/ 팀 화면에서 요청하는 API입니다.

채팅/ 팀 화면에서는 요청하는 API는 다음과 같습니다.

|name|type|
|------|---------------------------|
|채팅 정보|ChatAPI|
|채팅 메세지 보내기|ChatMessageAPI|
|팀 정보|TeamAPI|
|플레이어 정보|PlayerOfTeamAPI|

## Chat API

### URL

```
http://localhost:8080/clubs/{id}/chats
```

### Parameter

**Path Parameter**

```
/clubs/{id}/chats
```

**Query String Parameter**

```
//TBD
```

**Data Params**

```
//TBD
```

### Request

```
//TBD
```

### Response

**Success**

```
//TBD
```

**Error**

```
//TBD
```

## ChatMessage API

### URL

```
//TBD
```

### Parameter

**Path Parameter**

```
//TBD
```

**Query String Parameter**

```
none
```

**Data Params**

```
none
```

### Request

```
//TBD
```

### Response

**Success**

```
//TBD
```

**Error**

```
//TBD
```

## Team API

### URL

```
http://localhost:8080/matches/1/teams
```

### Parameter

**Path Parameter**

```
/clubs/{id}/teams
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
GET /matches/16/teams HTTP/1.1
Host: localhost:8080
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGci...
...

```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json
...

{
    "teams": [
        {
            "id": 1,
            "nickname": "nickname",
            "profile": {
                "profileUrl": "https://stardium2020.s3.ap-northeas...
            }
        },
        {
            "id": 2,
            "nickname": "nickname2",
            "profile": {
                "profileUrl": "https://stardium2020.s3.ap-northe...
            }
        },
        {
            "id": 5,
            "nickname": "nickname5",
            "profile": {
                "profileUrl": "https://stardium2020.s3.ap-northea...
            }
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
        "timestamp": "2020-06-24 21:37:04",
        "code": 402,
        "message": "해당 플레이어는 방에 없습니다."
    }
}
```

## PlayerOfTeam API

### URL

```
http://localhost:8080/matches/16/teams/1
```

### Parameter

**Path Parameter**

```
/clubs/{id}/teams
```
|key|value|
|------|------|
|id(방 아이디)|16|
|playerId(찾을 플레이어 아이디)|1|

**Query String Parameter**

* none

**Data Params**

* none

### Request

```
GET /matches/16/teams/4 HTTP/1.1
Host: localhost:8080
Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGci...
...

```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json
...

{
    "id": 1,
    "email": "test@test.com",
    "nickname": "nickname",
    "profile": {
        "profileUrl": "https://stardium2020.s..."
    },
    "statusMessage": ""
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
        "timestamp": "2020-06-24 21:57:52",
        "code": 101,
        "message": "사용자를 찾을 수 없습니다."
    }
}
```