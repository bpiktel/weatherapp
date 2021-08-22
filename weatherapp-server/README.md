# PAP21L-Z09
## Dostępne skrypty:

### `mvnw spring-boot:run`

Buduje i uruchamia serwer lokalnie na porcie 8080.\
Testowe zapytanie: [http://localhost:8080/forecast](http://localhost:8080/forecast)

### `mvn clean package spring-boot:repackage`

Tworzy w folderze `target` plik .jar pozwalający na uruchomienie aplikacji przez polecenie `java -jar `*`nazwa pliku`*


### `mvn test` lub `.\mvnw test`    
Uruchamia testy, których wyniki pojawiają się w konsoli oraz w folderze `target\surefire-reports` w formie plików .txt   

### `mvn site` lub `.\mvnw site`      
Tworzy raport ze zgodności kodu ze standardami w pliku `target\site\checkstyle.html`
