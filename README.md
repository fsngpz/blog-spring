# My Blog CRUD API

This is a simple CRUD (Create, Read, Update, Delete) API 
for managing blog posts. It provides endpoints to perform 
basic operations on blog posts, including creating, 
retrieving, updating, and deleting posts.

# Prerequisites

Before running the API, 
make sure you have the following installed:

- Java Development Kit (JDK) version 17 or higher
- Gradle

## Installation

1. Clone the repository:
    ```
    git clone https://github.com/fsngpz/blog-spring.git
   ```
2. Build the project using Gradle:
    ``` 
   ./gradlew
   ```

## Usage
Run the `BlogApplication`


The API will be accessible at http://localhost:8080

Blog Post API requires JWT Token. To get the JWT Token, you need to login.
I already set the default user the credential attached below:
```
username : user
password : user123
```

## Endpoints

### Login
- Endpoint: POST `/v1/api/auth/login`
- Request Body:
```json
{ 
   "username" : "user", 
   "password": "user123"
}

```

- Response Body:
```json
{
  "bearerToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjg1NTU3NjEzLCJleHAiOjE2ODU1NTk0MTN9.rh_xjWWAilIIXNd3XfI5JsOTQdjbnm10lRwQpZcPh7s"
}
```

### Create a Blog Post
- Endpoint: POST `/v1/api/archive`
- Authorization: `Bearer Token`
- Request Body:
```json
{ 
   "title" : "Hello World Dogs", 
   "body": "Lorem Ipsum, The Dogs Quick Brown", 
   "author" : "B Json"
}
```

- Response Body:
```json
{
  "archiveId": 4,
  "title": "Hello World Dogs",
  "body": "Lorem Ipsum, The Dogs Quick Brown",
  "publishedBy": "B Json",
  "publishedAt": "2023-06-01T00:39:51.672829+07:00"
}
```


### Get All Blog Post
- Endpoint: GET `/v1/api/archive`
- Authorization: `Bearer Token`
- Request Parameter

 | Params        | Description                      |
  |---------------|----------------------------------|
  | pageIndex     | The index of page                |
  | pageSize      | The size of page                 |
  | sortBy        | Sort the data by specified field |
  | sortDirection | Direction of sorting             |


- Response Body:
```json
{
  "content": [
    {
      "title": "Shaggy Dogs",
      "body": "Lorem Ipsum, The Dogs Quick Brown",
      "publishedBy": "B Json",
      "publishedAt": "2023-05-29T18:09:34.388168Z",
      "editedBy": "B Json",
      "editedAt": "2023-05-29T18:09:34.388187Z"
    },
    {
      "title": "Hello World Dogs",
      "body": "Lorem Ipsum, The Dogs Quick Brown",
      "publishedBy": "B Json",
      "publishedAt": "2023-05-31T17:39:51.672829Z",
      "editedBy": "B Json",
      "editedAt": "2023-05-31T17:39:51.672926Z"
    },
    {
      "title": "Hello World",
      "body": "Lorem Ipsum, The Dogs Quick Brown",
      "publishedBy": "Shaggy Dog",
      "publishedAt": "2023-05-29T18:09:38.530886Z",
      "editedBy": "Shaggy Dog",
      "editedAt": "2023-05-29T18:12:11.04889Z"
    }
  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 3,
  "last": true,
  "numberOfElements": 3,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "first": true,
  "empty": false
}
```

### Get a Blog Post
- Endpoint: GET `/v1/api/archive/id`
- Authorization: `Bearer Token`
- Path Variable: `id` of the blog post

- Response Body:
```json
{
  "title": "Hello World",
  "body": "Lorem Ipsum, The Dogs Quick Brown",
  "publishedBy": "Shaggy Dog",
  "publishedAt": "2023-05-29T18:09:38.530886Z",
  "editedBy": "Shaggy Dog",
  "editedAt": "2023-05-29T18:12:11.04889Z"
}
```

### Update a Blog Post
- Endpoint: PUT `/v1/api/archive/id`
- Authorization: `Bearer Token`
- Request Body:

```json
{ 
   "title" : "Hello World", 
   "body": "Lorem Ipsum, The Dogs Quick Brown", 
   "author" : "Shaggy Dog"
}

```

- Response Body:
```json
{
  "title": "Hello World",
  "body": "Lorem Ipsum, The Dogs Quick Brown",
  "publishedBy": "Shaggy Dog",
  "publishedAt": "2023-05-29T18:09:38.530886Z",
  "editedBy": "Shaggy Dog",
  "editedAt": "2023-06-01T01:19:23.122857+07:00"
}
```

### Delete a Blog Post
- Endpoint: DELETE `/v1/api/archive/id`
- Authorization: `Bearer Token`

- Response Body:
```
204 No Content
```


## List of Error Code
| Error Code                       | Description                                                         |
|----------------------------------|---------------------------------------------------------------------|
| `UHNANDLED_EXCEPTION`            | The general exception, unhandled error.                             |
| `ILLEGAL_ARGS_EXCEPTION`         | The illegal arguments exception, example: parameter null.           |
| `USERNAME_OR_PASSWORD_NOT_FOUND` | Thrown when the specified username and password is wrong.           |
| `ARCHIVE_NOT_FOUND`              | Thrown when the blog post not found.                                |
| `DUPLICATE_ARCHIVE`              | Thrown when the blog specified blog post already exist in database. |
