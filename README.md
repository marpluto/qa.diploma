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

### 4. Запустить программу Docker

### 5. Запуск проекта в MySQL
#### 5.1 Используя плагин для работы с Docker в IntelliJ IDEA запустить контейнеры `mysql` и `nodejs`

<details><summary>После прогона автотестов в MySQL</summary>
Завершить работу контейнеров командой Down.
</details>

#### 5.2 В файле [application.properties](application.properties) прописать в строке `spring.datasource.url=` значение `jdbc:mysql://localhost:3306/app`

#### 5.3 В классе [SQLHelper.java](src%2Ftest%2Fjava%2Fru%2Fnetology%2Fdata%2FSQLHelper.java) в строке `19` прописать `return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");`

#### 5.4 Открыть программу DBeaver. Создать новое соединение с БД MySQL, в качестве данных для подключения указать: База данных `app`, Пользователь `app`, Пароль `pass`, Порт `3306`

#### 5.5 В консоли IntelliJ IDEA запустить SUT командой `java -jar ./artifacts/aqa-shop.jar -P:jdbc url=jdbc:mysql://localhost:3306/app`
<details><summary>После прогона автотестов в MySQL</summary>
Завершить работу SUT, можно использовать сочетание клавиш ctrl+C (cmd+C для macOS).
</details> 

### 6. Запуск проекта в PostgreSQL
#### 6.1 Используя плагин для работы с Docker в IntelliJ IDEA запустить контейнеры `postgres` и `nodejs`

<details><summary>После прогона автотестов в Postgres</summary>
Завершить работу контейнеров командой Down.
</details>

#### 6.2 В файле [application.properties](application.properties) прописать в строке `spring.datasource.url=` значение `jdbc:postgresql://localhost:5432/app`

#### 6.3 В классе [SQLHelper.java](src%2Ftest%2Fjava%2Fru%2Fnetology%2Fdata%2FSQLHelper.java) в строке `19` прописать `return DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "app", "pass");`

#### 6.4 Открыть программу DBeaver. Создать новое соединение с БД PostgreSQL, в качестве данных для подключения указать: База данных `app`, Пользователь `app`, Пароль `pass`, Порт `5432`

#### 6.5 В консоли IntelliJ IDEA запустить SUT командой `java -jar ./artifacts/aqa-shop.jar -P:jdbc url=jdbc:postgresql://localhost:5432/app`
<details><summary>После прогона автотестов в MySQL</summary>
Завершить работу SUT, можно использовать сочетание клавиш ctrl+C (cmd+C для macOS).
</details>

### 7. Запустить автотесты
В консоли IntelliJ IDEA ввести команду `./gradlew clean test --info`

### 8. Посмотреть отчет Allure
В консоли IntelliJ IDEA ввести команду `./gradlew allureserve`