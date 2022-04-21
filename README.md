## Модуль 8. Хранение данных и организация безопасности
### Лекция 8.3: JDBC API, JdbcTemplate, NamedParametersJdbcTemplate

# Задача DAO слой

## Описание
Попрактикуемся в работе с Spring JDBC, попутно закрепляя уже пройденные темы. Вам надо написать приложение для работы с БД, использую скрипты, который вы написали во [втором задании](../../sql-agg/task/README.md)

1. Создайте spring boot приложение, с зависимостями на два starter'а - `spring-boot-starter-jdbc` и `spring-boot-starter-web`

2. Перенесите скрипт создания таблицы в файл `schema.sql`, чтобы spring boot автоматически создавал таблицу.

2. Перенесите скрипт запроса из второго задания в папку `resources`. Перепишите скрипт так, чтобы она возвращал `product_name` для именованного параметра `name`(а не только для `alexey`), который вы будете передавать в методы выполнения скрипта `NamedParameterJdbcTemplate` вместе со скриптом запроса.

3. Напишите репозиторий для работы с БД. Для этого:
- создайте класс и пометьте его аннотацией Repository, либо создайте бин репозитория в Java config классе
- добавьте в поле класса String, которое содержит ваше содержание вашего скрипта. Само содержание вы можете считать с помощью кода ниже. Вам надо будет передать в метод `read` название вашего скрипта, который лежит в папке `resources`. Например так: `read(myScript.sql)`.
- создайте метод `getProductName(String name)`, который будет принимать имя и возвращать название продукта из базы данных.
```java
private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
``` 

4. Напишите контроллер, с методом-обработчиком GET-метода запроса с маппингом на endpoint `/products/fetch-product`. В query params запроса будет приходить строковый параметр `name`, который вам надо будет передавать дальше в репозиторий. То есть, ваш метод должен уметь обрабатывать запрос вида `localhost:8080/products/fetch-product?name=Ivan`.
   Контроллер должен будет возвращать название продукта, который он получит от репозитория.

5. Написанные код выложите в отдельный репозиторий на гитхабе и прикрепите ссылку на него в домашнем задании.


# Задача Миграции*

## Описание
Теперь попрактикуемся в работе с мехиназмами миграции.

1. Вам надо переписать логику работы задания `DAO слой` так, чтобы ваше приложение работало с миграциями. Не важно выберете ли вы Flyway или Liquibase.

2. Написанные код выложите в отдельный репозиторий на гитхабе и прикрепите ссылку на него в домашнем задании.


# Задача Две таблицы с Hibernate

## Описание
Перепишем приложение из [задания по JDBC](https://github.com/netology-code/jd-homeworks/blob/master/jdbc/task1/README.md) на Hibernate.

1. Вам необходимо будет написать две `Entity`, соответствующие двум таблицам из задания. Учтите, что вам надо будет верно выбрать логику отношений этих Entity: `OneToOne`, `OneToMany`, `ManyToOne`, `ManyToMany`

2. Перепишите репозиторий, чтобы он теперь работал с `EntityManager`, а не через `NamedParameterJdbcTemplate`.

3. Написанные код выложите в тот же репозиторий, что и задача [DAO слой](https://github.com/netology-code/jd-homeworks/blob/master/jdbc/task1/README.md) на гитхабe, только создайте под нее другую ветку `hibernate` и прикрепите ссылку на нее в домашнем задании.


# Задача Миграции c Hibernate

## Описание
Теперь попрактикуемся в работе с мехиназмами миграции в Hibernate.

1. Вам надо адаптировать логику работы задания [DAO слой c Hibernate](https://github.com/netology-code/jd-homeworks/blob/master/hibernate/task1/README.md) так, чтобы ваше приложение работало одновременно и с миграциями, и с Hibernate. Не важно выберете ли вы Flyway или Liquibase.

3. Написанные код выложите в тот же репозиторий, что и первая задач [DAO слой c Hibernate](https://github.com/netology-code/jd-homeworks/blob/master/hibernate/task1/README.md) на гитхабe, только создайте под нее другую ветку `migration-hibernate` и прикрепите ссылку на нее в домашнем задании.