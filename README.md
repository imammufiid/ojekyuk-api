# API Specs

## Base url

```
https://ojekyuk-api.herokuapp.com/
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

### Location

| Name             | Endpoint               | Method | JWT token | Body and response                |
|------------------|------------------------|--------|-----------|----------------------------------|
| Search Location  | `api/location/search`  | `GET`  | no        | [example](#location---search)    |
| Reverse Location | `api/location/reverse` | `GET`  | no        | [example](#location---reverse)   |
| Get Route        | `api/location/route`   | `GET`  | yes       | [example](#location---get-route) |

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

## Location Examples
### Location - Search

```
GET /api/location/search
```

Body

```json
{
  "name": "cafe",
  "coordinate": "-6.8913187,107.5733093"
}
```

Response

```json
{
  "status": true,
  "message": "Success",
  "data": [
    {
      "name": "C-19 Coffee",
      "address": {
        "city": "Bandung Kota",
        "country": "Indonesia",
        "district": "Cicendo"
      },
      "coordinate": {
        "latitude": -6.89103,
        "longitude": 107.57376
      }
    },
    {
      "name": "Eskelapa Muda Ibu Setiayati",
      "address": {
        "city": "Bandung Kota",
        "country": "Indonesia",
        "district": "Cicendo"
      },
      "coordinate": {
        "latitude": -6.89188,
        "longitude": 107.57265
      }
    }
  ]
}
```

### Location - Reverse

```
GET /api/location/reverse
```

Body

```json
{
  "coordinate": "-6.8913187,107.5733093"
}
```

Response

```json
{
  "status": true,
  "message": "Success",
  "data": {
    "name": "23, Jalan Karya Bhakti IV, Cimahi 40522, Indonesia",
    "address": {
      "city": "Cimahi",
      "country": "Indonesia",
      "district": "Cimahi Tengah"
    },
    "coordinate": {
      "latitude": -6.88363,
      "longitude": 107.55016
    }
  }
}
```

### Location - Get Route

```
GET /api/location/reverse
```

Body

```json
{
  "origin": "-8.1442408,112.1435852",
  "destination": "-8.1047506,112.1675169"
}
```

Response

```json
{
  "status": true,
  "message": "Success",
  "data": {
    "route": [
      {
        "latitude": -8.144025,
        "longitude": 112.143632
      },
      {
        "latitude": -8.1439,
        "longitude": 112.14305
      },
      {
        "latitude": -8.14369,
        "longitude": 112.14212
      },
      {
        "latitude": -8.14266,
        "longitude": 112.14229
      },
      {
        "latitude": -8.14162,
        "longitude": 112.14249
      },
      {
        "latitude": -8.14057,
        "longitude": 112.1428
      },
      {
        "latitude": -8.14035,
        "longitude": 112.14289
      },
      {
        "latitude": -8.14043,
        "longitude": 112.14325
      },
      {
        "latitude": -8.14045,
        "longitude": 112.14335
      },
      {
        "latitude": -8.14066,
        "longitude": 112.14409
      },
      {
        "latitude": -8.14076,
        "longitude": 112.14449
      },
      {
        "latitude": -8.1408,
        "longitude": 112.1448
      },
      {
        "latitude": -8.14073,
        "longitude": 112.14481
      },
      {
        "latitude": -8.14068,
        "longitude": 112.14485
      },
      {
        "latitude": -8.14051,
        "longitude": 112.1451
      },
      {
        "latitude": -8.14047,
        "longitude": 112.14516
      },
      {
        "latitude": -8.14015,
        "longitude": 112.1457
      },
      {
        "latitude": -8.14007,
        "longitude": 112.14582
      },
      {
        "latitude": -8.13986,
        "longitude": 112.14613
      },
      {
        "latitude": -8.13982,
        "longitude": 112.1462
      },
      {
        "latitude": -8.13979,
        "longitude": 112.14642
      },
      {
        "latitude": -8.13965,
        "longitude": 112.14664
      },
      {
        "latitude": -8.13955,
        "longitude": 112.14681
      },
      {
        "latitude": -8.1395,
        "longitude": 112.14704
      },
      {
        "latitude": -8.13947,
        "longitude": 112.14732
      },
      {
        "latitude": -8.13947,
        "longitude": 112.14771
      },
      {
        "latitude": -8.13947,
        "longitude": 112.14845
      },
      {
        "latitude": -8.13942,
        "longitude": 112.14869
      },
      {
        "latitude": -8.13923,
        "longitude": 112.14889
      },
      {
        "latitude": -8.13875,
        "longitude": 112.1491
      },
      {
        "latitude": -8.13744,
        "longitude": 112.1496
      },
      {
        "latitude": -8.13695,
        "longitude": 112.14976
      },
      {
        "latitude": -8.13639,
        "longitude": 112.14996
      },
      {
        "latitude": -8.13631,
        "longitude": 112.14998
      },
      {
        "latitude": -8.13492,
        "longitude": 112.15037
      },
      {
        "latitude": -8.13416,
        "longitude": 112.1506
      },
      {
        "latitude": -8.1338,
        "longitude": 112.15071
      },
      {
        "latitude": -8.13315,
        "longitude": 112.15089
      },
      {
        "latitude": -8.13289,
        "longitude": 112.15096
      },
      {
        "latitude": -8.13216,
        "longitude": 112.15114
      },
      {
        "latitude": -8.13176,
        "longitude": 112.15125
      },
      {
        "latitude": -8.13142,
        "longitude": 112.15133
      },
      {
        "latitude": -8.13108,
        "longitude": 112.15141
      },
      {
        "latitude": -8.1303,
        "longitude": 112.15161
      },
      {
        "latitude": -8.12843,
        "longitude": 112.15204
      },
      {
        "latitude": -8.12812,
        "longitude": 112.15212
      },
      {
        "latitude": -8.12662,
        "longitude": 112.15249
      },
      {
        "latitude": -8.12507,
        "longitude": 112.15287
      },
      {
        "latitude": -8.12428,
        "longitude": 112.15308
      },
      {
        "latitude": -8.12351,
        "longitude": 112.15326
      },
      {
        "latitude": -8.12323,
        "longitude": 112.15334
      },
      {
        "latitude": -8.12305,
        "longitude": 112.15339
      },
      {
        "latitude": -8.12333,
        "longitude": 112.1543
      },
      {
        "latitude": -8.12342,
        "longitude": 112.15453
      },
      {
        "latitude": -8.12362,
        "longitude": 112.15513
      },
      {
        "latitude": -8.12378,
        "longitude": 112.15558
      },
      {
        "latitude": -8.12427,
        "longitude": 112.15687
      },
      {
        "latitude": -8.12465,
        "longitude": 112.15805
      },
      {
        "latitude": -8.12477,
        "longitude": 112.1584
      },
      {
        "latitude": -8.12445,
        "longitude": 112.15854
      },
      {
        "latitude": -8.12387,
        "longitude": 112.15887
      },
      {
        "latitude": -8.12278,
        "longitude": 112.15943
      },
      {
        "latitude": -8.12221,
        "longitude": 112.15974
      },
      {
        "latitude": -8.11876,
        "longitude": 112.16162
      },
      {
        "latitude": -8.11858,
        "longitude": 112.16172
      },
      {
        "latitude": -8.11783,
        "longitude": 112.16216
      },
      {
        "latitude": -8.1173,
        "longitude": 112.16243
      },
      {
        "latitude": -8.11705,
        "longitude": 112.16256
      },
      {
        "latitude": -8.11639,
        "longitude": 112.1629
      },
      {
        "latitude": -8.11615,
        "longitude": 112.16302
      },
      {
        "latitude": -8.11545,
        "longitude": 112.16338
      },
      {
        "latitude": -8.1153,
        "longitude": 112.16346
      },
      {
        "latitude": -8.11488,
        "longitude": 112.16369
      },
      {
        "latitude": -8.11407,
        "longitude": 112.16412
      },
      {
        "latitude": -8.11347,
        "longitude": 112.16448
      },
      {
        "latitude": -8.11337,
        "longitude": 112.16453
      },
      {
        "latitude": -8.11322,
        "longitude": 112.1646
      },
      {
        "latitude": -8.11315,
        "longitude": 112.16464
      },
      {
        "latitude": -8.11237,
        "longitude": 112.16508
      },
      {
        "latitude": -8.11145,
        "longitude": 112.16552
      },
      {
        "latitude": -8.11084,
        "longitude": 112.16584
      },
      {
        "latitude": -8.11017,
        "longitude": 112.16622
      },
      {
        "latitude": -8.10961,
        "longitude": 112.16652
      },
      {
        "latitude": -8.10948,
        "longitude": 112.1666
      },
      {
        "latitude": -8.10932,
        "longitude": 112.16672
      },
      {
        "latitude": -8.10926,
        "longitude": 112.16675
      },
      {
        "latitude": -8.10923,
        "longitude": 112.1668
      },
      {
        "latitude": -8.10923,
        "longitude": 112.16687
      },
      {
        "latitude": -8.10913,
        "longitude": 112.16687
      },
      {
        "latitude": -8.10884,
        "longitude": 112.16694
      },
      {
        "latitude": -8.10615,
        "longitude": 112.16752
      },
      {
        "latitude": -8.1061,
        "longitude": 112.16753
      },
      {
        "latitude": -8.10591,
        "longitude": 112.16758
      },
      {
        "latitude": -8.1057,
        "longitude": 112.16761
      },
      {
        "latitude": -8.1055,
        "longitude": 112.16766
      },
      {
        "latitude": -8.10475,
        "longitude": 112.16782
      },
      {
        "latitude": -8.10458,
        "longitude": 112.16787
      },
      {
        "latitude": -8.104526,
        "longitude": 112.167557
      }
    ]
  }
}
```
