Beberapa perubahan pom.xml ketika build
1. Tidak men-copy resource folder
2. Copy semua dependensi

Melakukan application packaging

```
mvn clean package
```

Output akan membentuk sebuah folder target dan yang kita butuhkan adalah file jar aplikasi kita dan folder libs

Untuk menjalankan aplikasi

```
spring_profiles_active=prod java -cp "libs/*:hello-spring-1.1.0-SNAPSHOT.jar" -Dapp.home="/Users/edwardsuwirya/Projects/Java/hello-spring/target/" com.enigmacamp.Application
```
