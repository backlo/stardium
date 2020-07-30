# 로그인 화면 API 모음

### OverView
로그인 화면에서 요청하는 API입니다.

로그인 화면에서는 요청하는 API는 다음과 같습니다.

|name|type|
|------|---------------------------|
|일반 로그인|LoginAPI|
|카카오 로그인|KakaoLoginAPI|

## Login API


### URL

```
http://localhost:8081/login
```

### Parameter

**Path Parameter**

* none

**Query String Parameter**

* none

**Data Params(application/json)**

|key|value|
|------|------|
|email|이메일|
|password|비밀번호|

### Request

```
POST /login HTTP/1.1
Host: localhost:8081
...

{
    "email": "test@test.com",
    "password": "password"
}
```

### Response

**Success**

```
HTTP/1.1 201 OK
Content-Type: application/json
...

{
    "token": "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0NUB0ZXN0LmNv...",
    "email": "test5@test.com",
    "nickname": "nickname5",
    "profile": {
        "profileUrl": "https://stardium2020.s3.ap-northeast-2.amazonaws.com/p..."
    }
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
        "timestamp": "2020-06-08 22:04:18",
        "code": 103,
        "message": "비밀번호가 맞지 않습니다."
    }
}
```


