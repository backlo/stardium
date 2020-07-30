# 회원가입 화면 API 모음

### OverView
회원가입 화면에서 요청하는 API입니다.

회원가입 화면에서는 요청하는 API는 다음과 같습니다.

|name|type|
|------|---------------------------|
|회원가입 정보|NewPlayerAPI|

## NewPlayer API


### URL

```
http://localhost:8080/players
```

### Parameter

**Path Parameter**

```
none
```

**Query String Parameter**

```
none
```

**Data Params**

```
{
  "email":"test@test.com",
  "password":"password"
  "nickname" : "nickname"
}
```

### Request

```
POST /players HTTP/1.1
Host: localhost:8080

{
  "email":"test@test.com",
  "password":"password"
  "nickname" : "nickname"
}
```

### Response

**Success**

```
HTTP/1.1 200 OK
Content-Type: application/json
...

{
  "token" : "ADKFNVK23N4JDKVP19VNDRF92N"
}
```

**Error**

```
HTTP/1.1 400 OK
Content-Type: application/json
...

{
  "error" : {
  	"message": "password is not valid.", 
  	"type": "PasswordNotValidException",
  }
}
```


