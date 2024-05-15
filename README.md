1) Kullanıcı Kaydı:
   
İstemci kullanıcı adı ve parola ile bir POST isteği gönderir.

![register_POST](https://github.com/zeyhznc/Online-Book-Management-System/assets/68854214/9acbed30-f8e7-4510-84f2-5fffef958f3d)

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

  -- Doğrulama geçerse , kullanıcıya ait bilgiler _customer tablosuna kaydedilir. 

![Success_DB](https://github.com/zeyhznc/Online-Book-Management-System/assets/68854214/416ddf25-77ce-4d29-a691-e74c9feacfbf)

2) Kullanıcı Girişi:

İstemci kullanıcı adı ve parola ile bir post isteği gönderir.

![auth_POST](https://github.com/zeyhznc/Online-Book-Management-System/assets/68854214/af99eec1-3a80-470a-a30b-f51461c3b0db)

Kullanıcıya ait bilgiler:

  {
   
    "email":  "zeynephazneci_3@gmail.com",
    
    "password": "password123456"
    
  }


Sunucu , kullanıcı bilgiilerini veritabanında depolanan verilerle karşılaştırır. Az önce veritabanına kaydedilen kullanıcı bilgileri ile bu POST isteğinde gönderdiğimiz bilgiler tutarlı olduğu için, response olarak JWT belirteci döner.


