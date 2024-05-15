1) Kullanıcı Kaydı:
İstemci kullanıcı adı ve parola ile bir POST isteği gönderir.
![register_POST](https://github.com/zeyhznc/Online-Book-Management-System/assets/68854214/bddf3e6e-ea60-421a-b8ea-5e9bcb763578)

-- Burada POST isteği için kullanılan url: http://localhost:8080/api/v1/auth/register

-- Kullanıcıya ait bilgiler:

  {
    "firstName": "Zeynep_3",
    
    "lastName": "Hazneci_3",
    
    "email":  "zeynephazneci_3@gmail.com",
    
    "password": "password123456",
    
    "role":  "ADMIN"
  }
   
-- Response olarak bu kullanıcıya ait oluşturulan access_token ve refresh_token bilgisi döner:

  {
      "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6ZXluZXBoYXpuZWNpXzNAZ21haWwuY29tIiwiaWF0IjoxNzE1Nzg0MDk3LCJleHAiOjE3MTU4NzA0OTd9.FlcM_4v6DMnH1HhlNKpdg0L_lv9damDmkXpz30S1-14",
     
      "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6ZXluZXBoYXpuZWNpXzNAZ21haWwuY29tIiwiaWF0IjoxNzE1Nzg0MDk3LCJleHAiOjE3MTgzNzYwOTd9.x1O29-6Mv7r9Fu29Ur7eW4x0YoVtbtaP_v7aiQqrUgo"
  }
