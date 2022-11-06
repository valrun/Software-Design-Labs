# Lab-3

## Условия
### Цель: 
получить практический опыт применения техник рефакторинга.

Скачайте приложение: https://github.com/akirakozov/software-design/tree/master/java/refactoring

Приложение представляет собой простой web-server, который хранит информацию о товарах и 
их цене. Поддержаны такие методы:
* http://localhost:8081/get - products - посмотреть все товары в базе
* http://localhost:8081/add - product ? name = iphone 6& price =300 - добавить новый товар
* http://localhost:8081/query? command = sum - выполнить некоторый запрос с данными в базе

Необходимо отрефакторить этот код (логика методов не должна измениться),
например:
* убрать дублирование
* выделить отдельный слой работы с БД
* выделить отдельный слой формирования html-ответа
* и тд

### Указание:
* задание нужно сдавать через e-mail, в заголовке письма указать “[SD-TASK]”
* проект перенести к себе github.com
* сначала добавить тесты (отдельными комитами)
* каждый отдельный рефакторинг делать отдельным комитом
* без истории изменений и тестов баллы буду сниматься

### Решение
* [История коммитов](https://github.com/valrun/Software-Design-Labs/commits/main). После инициализации репозитория коммиты имеют префик названия лаборатрной, к которой они относятся
* Добавлены тесты на каждый сервлет:
  * [GetProductsServletTest](refactoring/src/test/java/ru/akirakozov/sd/refactoring/servlet/GetProductsServletTest.java)
  * [AddProductServletTest](refactoring/src/test/java/ru/akirakozov/sd/refactoring/servlet/AddProductServletTest.java)
  * [QueryServletTest](refactoring/src/test/java/ru/akirakozov/sd/refactoring/servlet/QueryServletTest.java)
* Все строки вынесены в константы (разделены по предназначению):
  * [CommonAbstractServlet](refactoring/src/main/java/ru/akirakozov/sd/refactoring/servlet/CommonAbstractServlet.java)
  * [ProductDao](refactoring/src/main/java/ru/akirakozov/sd/refactoring/dao/ProductDao.java)
  * [HttpMessage](refactoring/src/main/java/ru/akirakozov/sd/refactoring/l10n/HttpMessage.java)
* Вся работа с базой данных вынесена в [ProductDao](refactoring/src/main/java/ru/akirakozov/sd/refactoring/dao/ProductDao.java)
* Формирование сообщений вынесено в [HttpMessage](refactoring/src/main/java/ru/akirakozov/sd/refactoring/l10n/HttpMessage.java)
* Строки тестов вынесены в константы в [TestUtils](refactoring/src/test/java/ru/akirakozov/sd/refactoring/servlet/TestUtils.java). (Раньше это не делалось, чтобы быть уверенным в правильности тестов)