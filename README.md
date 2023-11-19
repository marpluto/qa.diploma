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
- 5.1 Используя плагин для работы с Docker в IntelliJ IDEA запустить контейнеры `mysql` и `nodejs`

![Screenshot_22](https://github.com/marpluto/qa.diploma/assets/120597031/1017d250-e19b-401e-a47c-916d04539c6b)


<details><summary>После прогона автотестов в MySQL</summary>
  Завершить работу контейнеров командой Down
</details>


- 5.2 В файле [application.properties](application.properties) прописать в строке `spring.datasource.url=` значение `jdbc:mysql://localhost:3306/app`

![Screenshot_26](https://github.com/marpluto/qa.diploma/assets/120597031/b8cd8a0f-e341-4e1f-9ec3-1e09d82a6da9)

- 5.3 В классе [SQLHelper.java](src%2Ftest%2Fjava%2Fru%2Fnetology%2Fdata%2FSQLHelper.java) в строке `19` прописать `return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");`

![Screenshot_27](https://github.com/marpluto/qa.diploma/assets/120597031/b7ce788b-e81d-4250-bf8e-7630f1a7b7ef)


- 5.4 Открыть программу DBeaver. Создать новое соединение с БД MySQL, в качестве данных для подключения указать: База данных `app`, Пользователь `app`, Пароль `pass`, Порт `3306`

![Screenshot_31](https://github.com/marpluto/qa.diploma/assets/120597031/2c14be72-5509-4bf4-9f7c-31c8d64ca359)


- 5.5 В консоли IntelliJ IDEA запустить SUT командой `java -jar ./artifacts/aqa-shop.jar -P:jdbc url=jdbc:mysql://localhost:3306/app`
<details><summary>После прогона автотестов в MySQL</summary>
Завершить работу SUT, можно использовать сочетание клавиш ctrl+C (cmd+C для macOS).
</details> 

### 6. Запуск проекта в PostgreSQL
- 6.1 Используя плагин для работы с Docker в IntelliJ IDEA запустить контейнеры `postgres` и `nodejs`

![Screenshot_23](https://github.com/marpluto/qa.diploma/assets/120597031/d636f843-3b15-4099-ac5b-ae9ac5f8fa83)


<details><summary>После прогона автотестов в Postgres</summary>
Завершить работу контейнеров командой Down.
</details>

- 6.2 В файле [application.properties](application.properties) прописать в строке `spring.datasource.url=` значение `jdbc:postgresql://localhost:5432/app`

![Screenshot_24](https://github.com/marpluto/qa.diploma/assets/120597031/b908bb50-a13c-4a48-9247-5e6c5b63626b)


- 6.3 В классе [SQLHelper.java](src%2Ftest%2Fjava%2Fru%2Fnetology%2Fdata%2FSQLHelper.java) в строке `21` прописать `return DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "app", "pass");`

![Screenshot_25](https://github.com/marpluto/qa.diploma/assets/120597031/1e7f6711-5c7d-4d85-98ee-161388ba664b)


- 6.4 Открыть программу DBeaver. Создать новое соединение с БД PostgreSQL, в качестве данных для подключения указать: База данных `app`, Пользователь `app`, Пароль `pass`, Порт `5432`

![Screenshot_28](https://github.com/marpluto/qa.diploma/assets/120597031/dddcb3c6-a024-470a-b6c1-36d986094494)


- 6.5 В консоли IntelliJ IDEA запустить SUT командой `java -jar ./artifacts/aqa-shop.jar -P:jdbc url=jdbc:postgresql://localhost:5432/app`
<details><summary>После прогона автотестов в MySQL</summary>
Завершить работу SUT, можно использовать сочетание клавиш ctrl+C (cmd+C для macOS).
</details>

### 7. Запустить автотесты
В консоли IntelliJ IDEA ввести команду `./gradlew clean test --info`

### 8. Посмотреть отчет Allure
В консоли IntelliJ IDEA ввести команду `./gradlew allureserve`
