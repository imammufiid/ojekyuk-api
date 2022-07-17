# API Specs

## Base url

```
https://ojekyuk-api.herokuapp.com/v1
```

## Table endpoints
### User Customer

| Name              | Endpoint                | Method   | JWT token | Body and response                             |
|-------------------|-------------------------|----------|-----------|-----------------------------------------------|
| Register customer | `api/customer/register` | `POST`   | no        | [example](#user-customer---register)          |
| Login customer    | `api/customer/login`    | `POST`   | no        | [example](#user-customer---login)             |
| Get customer info | `api/customer`          | `GET`    | yes       | [example](#user-customer---get-customer-info) |
| Update customer   | `api/customer`          | `UPDATE` | yes       | [example](#user-customer---update-customer)   |

### User Driver

| Name            | Endpoint              | Method   | JWT token | Body and response                         |
|-----------------|-----------------------|----------|-----------|-------------------------------------------|
| Register driver | `api/driver/register` | `POST`   | no        | [example](#user-driver---register)        |
| Login driver    | `api/driver/login`    | `POST`   | no        | [example](#user-driver---login)           |
| Get driver info | `api/driver`          | `GET`    | yes       | [example](#user-driver---get-driver-info) |
| Update driver   | `api/driver`          | `UPDATE` | yes       | [example](#user-driver---update-driver)   |

---

## User Customer Examples
### User Customer - Register

```
POST /api/user/customer/register
```

Body

```json
{
  "username": "imam",
  "password": "1234"
}
```

Response

```json
{
  "success": true,
  "message": "Add user success!",
  "data": {
    "expiredAt": 1622885455368,
    "updatedAt": 1622885435453,
    "role": "1",
    "username": "imam",
    "id": "1dcdcf58-99a5-4965-8b46-10a2429726b0",
    "password": "$2b$10$w/jwx5LVgFPpvUOqE/v15e184Qp1DANfX.Y01qL1Mrb9owg5.3wuG"
  }
}
```

### User Customer - Login

```
POST /api/user/customer/login
```

Body

```json
{
  "username": "imam",
  "password": "1234"
}
```

Response

```json
{
  "success": true,
  "message": "Login success!",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcmVkQXQiOjE2MjI4ODU3MzIsImlkIjoiNTY0NGE5YmYtYThkZS00N2FkLTg1MTMtZmNkZTgyYTQ4ZGYyIiwicGFzc3dvcmQiOiIkMmIkMTAkdy9qd3g1TFZnRlBwdlVPcUUvdjE1ZTE4NFFwMURBTmZYLlkwMXFMMU1yYjlvd2c1LjN3dUciLCJyb2xlIjoiY3VzdG9tZXIiLCJ1cGRhdGVkQXQiOjE2MjI3OTUwMzkxOTUsInVzZXJuYW1lIjoibmlhciIsImlhdCI6MTYyMjg4NDc4OCwiZXhwIjoxNjIyODg2NTg4fQ.38GxoDpPxLxwuDA9yrlqkwyqucXgcHi7mBXixGlKOmU"
  }
}
```

### User Customer - Get customer info

```
GET /api/user/customer
HEADER Authorization eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcmVkQXQiOjE2MjI....
```

Response

```json
{
  "success": true,
  "message": "Get user success!",
  "data": {
    "expiredAt": 1623265384,
    "id": "5644a9bf-a8de-47ad-8513-fcde82a48df2",
    "password": "$2b$10$w/jwx5LVgFPpvUOqE/v15e184Qp1DANfX.Y01qL1Mrb9owg5.3wuG",
    "role": "1",
    "updatedAt": 1622795039195,
    "username": "imam"
  }
}
```

### User Customer - Update customer 

```
UPDATE /api/user/customer/
HEADER Authorization eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcmVkQXQiOjE2MjI....
```

Body

```json
{
  "username": "imam",
  "password": "1234"
}
```

Response

```json
{
  "success": true,
  "message": "Update user success!",
  "data": {
    "expiredAt": 1623265384,
    "id": "5644a9bf-a8de-47ad-8513-fcde82a48df2",
    "password": "$2b$10$w/jwx5LVgFPpvUOqE/v15e184Qp1DANfX.Y01qL1Mrb9owg5.3wuG",
    "role": "1",
    "updatedAt": 1622795039195,
    "username": "imam"
  }
}
```

---

## User Driver Examples
### User Driver - Register

```
POST /api/user/driver/register
```

Body

```json
{
  "username": "imam",
  "password": "1234"
}
```

Response

```json
{
  "success": true,
  "message": "Add user success!",
  "data": {
    "expiredAt": 1622885455368,
    "updatedAt": 1622885435453,
    "role": "2",
    "username": "imam",
    "id": "1dcdcf58-99a5-4965-8b46-10a2429726b0",
    "password": "$2b$10$w/jwx5LVgFPpvUOqE/v15e184Qp1DANfX.Y01qL1Mrb9owg5.3wuG"
  }
}
```

### User Driver - Login

```
POST /api/user/driver/login
```

Body

```json
{
  "username": "imam",
  "password": "1234"
}
```

Response

```json
{
  "success": true,
  "message": "Login success!",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcmVkQXQiOjE2MjI4ODU3MzIsImlkIjoiNTY0NGE5YmYtYThkZS00N2FkLTg1MTMtZmNkZTgyYTQ4ZGYyIiwicGFzc3dvcmQiOiIkMmIkMTAkdy9qd3g1TFZnRlBwdlVPcUUvdjE1ZTE4NFFwMURBTmZYLlkwMXFMMU1yYjlvd2c1LjN3dUciLCJyb2xlIjoiY3VzdG9tZXIiLCJ1cGRhdGVkQXQiOjE2MjI3OTUwMzkxOTUsInVzZXJuYW1lIjoibmlhciIsImlhdCI6MTYyMjg4NDc4OCwiZXhwIjoxNjIyODg2NTg4fQ.38GxoDpPxLxwuDA9yrlqkwyqucXgcHi7mBXixGlKOmU"
  }
}
```

### User Driver - Get driver info

```
GET /api/user/driver
HEADER Authorization eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcmVkQXQiOjE2MjI....
```

Response

```json
{
  "success": true,
  "message": "Get user success!",
  "data": {
    "expiredAt": 1623265384,
    "id": "5644a9bf-a8de-47ad-8513-fcde82a48df2",
    "password": "$2b$10$w/jwx5LVgFPpvUOqE/v15e184Qp1DANfX.Y01qL1Mrb9owg5.3wuG",
    "role": "2",
    "updatedAt": 1622795039195,
    "username": "imam"
  }
}
```

### User Driver - Update driver

```
UPDATE /api/user/driver/
HEADER Authorization eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcmVkQXQiOjE2MjI....
```

Body

```json
{
  "username": "imam",
  "password": "1234"
}
```

Response

```json
{
  "success": true,
  "message": "Update user success!",
  "data": {
    "expiredAt": 1623265384,
    "id": "5644a9bf-a8de-47ad-8513-fcde82a48df2",
    "password": "$2b$10$w/jwx5LVgFPpvUOqE/v15e184Qp1DANfX.Y01qL1Mrb9owg5.3wuG",
    "role": "2",
    "updatedAt": 1622795039195,
    "username": "imam"
  }
}
```
