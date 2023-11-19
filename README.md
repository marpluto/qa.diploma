# Дипломный проект по профессии «Тестировщик»
[План автоматизации](https://github.com/marpluto/qa.diploma/blob/master/Plan.md)

## Процедура запуска автотестов
### 1. Необходимое для запуска окружение
- **IntelliJ IDEA**
- **Java: OpenJDK 11**
- **Docker**
- **DBeaver**
- **Google Chrome**

### 2. Склонировать репозиторий
Ввести в консоли команду `git clone https://github.com/marpluto/qa.diploma.git`

### 3. Открыть проект в IntelliJ IDEA

### 4. Docker
- Запустить программу Docker
- В консоли IntelliJ IDEA ввести команду `docker-compose up`
<details><summary>Чтобы завершить работу контейнеров</summary>
В консоли IntelliJ IDEA ввести команду `docker-compose down`
</details>

### 5. Подключиться к СУБД
- Открыть программу DBeaver
- Создать новое соединения с БД MySQL, в качестве данных для подключения указать: Database `app`, User `app`, Password `pass`, Port `3306`
- Создать новое соединения с БД PostgreSQL, в качестве данных для подключения указать: Database `app`, User `app`, Password `pass`, Port `5432`

### 6. Запустить SUT
####  На базе MySQL
В консоли IntelliJ IDEA ввести команду `java -jar ./artifacts/aqa-shop.jar -P:jdbc url=jdbc:mysql://localhost:3306/app`
<details><summary>Чтобы завершить запуск SUT</summary>
Использовать сочетание клавиш ctrl+C (cmd+C для macOS)
</details> 

####  На базе PostgreSQL
В консоли IntelliJ IDEA ввести команду `java -jar ./artifacts/aqa-shop.jar -P:jdbc url=jdbc:postgresql://localhost:5432/app`
<details><summary>Чтобы завершить запуск SUT</summary>
Использовать сочетание клавиш ctrl+C (cmd+C для macOS)
</details> 

### 6. Запустить автотесты
В консоли IntelliJ IDEA ввести команду `./gradlew clean test --info`

### 6. Посмотреть отчет Allure
В консоли IntelliJ IDEA ввести команду `./gradlew allureserve`