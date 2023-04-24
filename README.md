
# Project Postulacion EY - BACKEND

A continuacion indicare el proceso para ejecutar el proyecto.




## Base de datos H2

 - [Schema.sql](https://firebasestorage.googleapis.com/v0/b/apprazoi.appspot.com/o/SCHEMA%2FScript%20Schema.sql?alt=media&token=c60d51e1-81ee-48af-86ba-f3e1efb28941)

## API Reference

#### Create user
```http
  POST /user
```
##### Request Sample
```JSON
{
   "name":"Juan Rodriguez",
   "email":"juan@rodriguez.org",
   "password":"aTr2uc2",
   "phones":[
      {
         "number":"1234567",
         "citycode":"1",
         "contrycode":"57"
      }
   ]
}
```
##### Response Sample
```JSON
{
  "id": "34f09d97-d272-401f-9488-8666ddf2d6d8",
  "created": "2023-04-08T15:26:07.369+00:00",
  "modified": "2023-04-08T15:26:07.369+00:00",
  "last_login": "2023-04-08T15:26:07.369+00:00",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQGRmZDQ0Lm9yZyIsImV4cCI6MTY4MTA1Mzk2N30.FUfZofYq0nyl1OyGsSOthUAYTip1OwKXFrdHhXx7-Iy-sf-1ex0J5SUWmnWOVmjXcn5Rn8YCGqj_PvFaOuHI3Q",
  "isactive": true
}
```

#### Get User

```http
  GET /user/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of user to fetch |

##### Request Sample
```JSON
{
   "id": "061f7117-bb25-4f48-8c6d-85485e689351",
   "name":"Juan Rodriguez",
   "email":"juan@rodriguez.org",
   "password": "aTr2uc2",
   "created": "2023-04-08T16:34:22.481+00:00",
   "modified": "2023-04-08T16:34:22.481+00:00",
   "last_login": "2023-04-08T16:34:22.481+00:00",
   "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQGRmZDQ0Lm9yZyIsImV4cCI6MTY4MTA1ODA2Mn0.V4g_JCv2iB6YnkS0CBJkQYZ62fZG4S3js9kP-9xqWtJMRlSutIeqJKHCUwFdxqm63nenm06Di1MBM7LjAQKNxQ",
   "isactive": true,
   "phones":[
      {
         "number":"1234567",
         "citycode":"1",
         "contrycode":"57"
      }
   ]
}
```
### Get All Users

```http
  GET /allUser
```
##### Request Sample
```JSON
[
   {
      "id":"92ce7214-c449-4d18-9920-20df9f2fa2f7",
      "name":"Juan Rodriguez",
      "email":"juxanc@df51d44s.org",
      "password":"aTr2uc2s",
      "created":"2023-04-23T22:26:06.728+00:00",
      "modified":"2023-04-23T22:26:06.728+00:00",
      "last_login":"2023-04-23T22:26:06.728+00:00",
      "token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdXhhbmNAZGY1MWQ0NHMub3JnIiwiZXhwIjoxNjgyMzc1MTY2fQ.2ZsfufdkdNxmpwB4Emjc54P_QQfGui5E4Pf76CZ-he7H3oq85bJZe_lhsWnS2yHea3XZjUpgihhaf066gNIngg",
      "isactive":true,
      "phones":[]
   },
   {
      "id":"1592c205-1327-4936-ab7d-a0d8c7859803",
      "name":"Juan Rodriguez",
      "email":"juxan@df51d44s.org",
      "password":"aTr2uc2s",
      "created":"2023-04-23T22:26:09.211+00:00",
      "modified":"2023-04-23T22:26:09.211+00:00",
      "last_login":"2023-04-23T22:26:09.211+00:00",
      "token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdXhhbkBkZjUxZDQ0cy5vcmciLCJleHAiOjE2ODIzNzUxNjl9.r4dXCfAP8GFgQ48BZ8BimcWMz0fenBcc-IchwqppgaDFg5FtmJXWtFbsu-I_s3MknUxsG1lzHiIQp3vKowp7zQ",
      "isactive":true,
      "phones":[]
   }
]
```

#### Update user
```http
  PUT /user
```
##### Request Sample
```JSON
{
   "name":"Juan Rodriguez",
   "email":"juan@rodriguez.org",
   "password":"aTr2uc2"
}
```
##### Response Sample
```JSON
{
  "id": "40f27e6c-3fb2-49bb-a6a9-9f8da451f250",
  "created": "2023-04-08T16:41:42.620+00:00",
  "modified": "2023-04-08T16:41:58.004+00:00",
  "last_login": "2023-04-08T16:41:42.620+00:00",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQGRmNTFkNDRzLm9yZyIsImV4cCI6MTY4MTA1ODUwMn0.mkeCRH_KB2uD_JBNTXovKOAyx2QfTWvoh9xj-KvxL3hsCawX31Etd2DIEAb3EchFITvtChBgoSOTGGg3XhHgSA",
  "isactive": true
}
```
#### Delete User
```http
  DELETE /user/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of user to fetch |

##### Response Sample
```JSON
{
  "id": "40f27e6c-3fb2-49bb-a6a9-9f8da451f250",
  "created": "2023-04-08T16:41:42.620+00:00",
  "modified": "2023-04-08T16:41:58.004+00:00",
  "last_login": "2023-04-08T16:41:42.620+00:00",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQGRmNTFkNDRzLm9yZyIsImV4cCI6MTY4MTA1ODUwMn0.mkeCRH_KB2uD_JBNTXovKOAyx2QfTWvoh9xj-KvxL3hsCawX31Etd2DIEAb3EchFITvtChBgoSOTGGg3XhHgSA",
  "isactive": true
}
```


## Running Tests

To run tests, run the following command

```bash
  ./gradle test
```



