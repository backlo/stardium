# 프로필 수정 화면 API 모음

### OverView
프로필 수정 화면에서 요청하는 API입니다.

프로필 수정 화면에서는 요청하는 API는 다음과 같습니다.

|name|type|
|------|---------------------------|
|프로필 정보|ProfileInfoAPI|
|프로필 사진 수정|EditProfileImageAPI|
|프로필 수정|EditProfileAPI|

## ProfileInfo API


### URL

```
http://localhost:8081/players/profile
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
GET /players/profile HTTP/1.1
Host: localhost:8081
Authorization: "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUB0ZXN0LmNvb..."
```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json

...
{
    "email": "test4@test.com",
    "nickname": "nickname4",
    "profile": {
        "profileUrl": "https://stardium2020.s..."
    },
    "statusMessage": "안녕하세요~!"
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
        "timestamp": "2020-06-11 00:10:47",
        "code": 100,
        "message": "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted."
    }
}
```

## EditProfileImageAPI

### URL

```
http://localhost:8082/static/profile
```

### parameter

**Path Parameter**

* none

**Query String Parameter**

* none

**Data Params(form-data)**

|key|value|
|------|------|
|file|이미지 파일|

### Request

```
PUT /static/profile HTTP/1.1
Host: localhost:8082
Authorization: "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUB0ZXN0LmNvb..."
Content-Type: multipart/form-data; boundary=------WebKitFormBoundaryQG...
...

```

### Response

**Success**

```
HTTP/1.1 201 OK
Content-Type: application/json
...

{
    "imageUrl": "https://stardium2020.s3.ap-northeast-2.amazonaws.com/..."
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
        "timestamp": "2020-06-15 17:34:10",
        "code": 100,
        "message": "Invalid Jwt Token : Can't Accessed"
    }
}
```

## EditProfile API


### URL

```
http://localhost:8081/players/profile
```

### Parameter

**Path Parameter**

* none

**Query String Parameter**

* none

**Data Params(form-data)**

|key|value|
|------|------|
|password|패스워드|
|nickname|닉네임|
|statusMessage|상태 메세지 정보|

### Request

```
PUT /players/profile HTTP/1.1
Host: localhost:8081
Authorization: "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUB0ZXN0LmNvb..."
...

{
    "password": "",
    "nickname": "",
    "statusMessage": "안녕하세요~! 반가워요"
}
```

### Response

**Success**

```
HTTP/1.1 201 OK
Content-Type: application/json
...

{
    "email": "test2@test.com",
    "nickname": "nickname2",
    "profile": {
        "profileUrl": "https://stardium2020...."
    },
    "statusMessage": "안녕하세요~! 반가워요"
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
        "timestamp": "2020-06-11 00:05:10",
        "code": 103,
        "message": "닉네임이 존재 합니다."
    }
}
```

