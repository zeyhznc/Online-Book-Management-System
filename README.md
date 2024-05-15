**PROJE YAPISI**

1) Bu proje Java dilinde geliştirilmiştir ve Spring Boot kullanılarak oluşturulmuştur.

2) Projenin çalışması için şu Maven bağımlılıkları gerekir :

   Spring Boot

   Spring Web

   Spring Data JPA

Proje bağımlılıkları pom.xml dosyasında şu şekilde tanımlanmıştır:

```pom.xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.2.5</version>
		<relativePath/>
		<!--  lookup parent from repository  -->
	</parent>
	<groupId>com.myproject</groupId>
	<artifactId>my-online-book-management-project</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>my-online-book-management-project</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
<!--			<scope>test</scope>-->
    		<version>6.2.4</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
	        <groupId>io.springfox</groupId>
	        <artifactId>springfox-boot-starter</artifactId>
	        <version>3.0.0</version>
	    </dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-maven-plugin</artifactId>
					<configuration>
							<excludes>
									<exclude>
											<groupId>org.projectlombok</groupId>
											<artifactId>lombok</artifactId>
									</exclude>
							</excludes>
					</configuration>
			</plugin>
		</plugins>
	</build>
</project>

```
**PROJE KURULUMU**




**AKIŞ**

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


