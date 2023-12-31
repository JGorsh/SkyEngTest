# Тестовое задание Skyeng (Горшков Алексей)

[Swagger Api Documentation](https://github.com/JGorsh/SkyEngTest/blob/3c819f7b2f11a9ef0aab8f86f8f82334f9390ac6/api-documentation%20(1).pdf)

## База данных использовалась Н2: 
(также используется data.sql скрипт для заполнения таблицы PostOffice 
для проверки работы API):
```
datasource:
url: jdbc:h2:~/sk
username: sa
password: sa

jpa:
defer-datasource-initialization: true
hibernate:
ddl-auto: update
show-sql: true

h2:
console:
enabled: true
path: /h2
```
## Запросы и эталонные JSON:
#### Алгорим действий
###### (UUID и время генерируется автоматически при регестрации)
1) Регистация посылки (PostOffice указывается в котором происходит регестрация)
2) Отправка посылки из почтового отделения (PostOffice указывается куда отправляется посылка)
3) Прибытие посылки в почтовое отделение
4) Если отделение совпадает с отделением получателя - получение адресатом 
5) Если не совпадает далее пункт 2 и 3

#### Была предусмотрена работа со статусами:
- Нельзя получить посылку адресатом в другом офисе
- Нельзя отправить почту, уже отправленную
- Нельзя зафиксировать отправление в отделении если посылка не в пути к ней
- Нельзя повторно отправить/зафиксировать/получить отправление если уже эта операция была сделана.
- Работа с почтовым отправлением следует определенной последовательности действий
  
### Регистрация нового почтового отправления
#### ВНИМАНИЕ! (При регестрации сгенерируется UUID в Response, его нужно будет использовать в последующих запросах для работы с этой посылкой)
### POST
```
/mailing/registration
```
```
{
"mailType":"LETTER",
"mailIndexRecipient":"460000",
"recipientName":"Alex",
"recipientAddress":"Moscow",
"postOffice":{
    "id":5
}
}
```
### Убытие почтового отправления из отделения
#### ВНИМАНИЕ! (Используйте #UUID из ответа при регистрации!)
### PUT
```
/mailing/departure
```
```
{
    "uuid":"???????????",
    "postOffice": {
        "id": 1
    }
}
```
### Прибытие почтового отправления в отделение
#### ВНИМАНИЕ! (Используйте #UUID из ответа при регистрации!)
### PUT
```
/mailing/arrival
```
```
{
    "uuid":"???????????",
    "postOffice": {
        "id": 1
    }
}
```
### Получение отправления получателем
#### ВНИМАНИЕ! (Используйте #UUID из ответа при регистрации!)
### PUT
```
/mailing/receiving
```
```
{
    "uuid":"???????????",
    "mailIndexRecipient":"460000",
    "recipientName":"Alex",
    "recipientAddress":"Moscow",
    "postOffice": {
        "id": 1
    }
}
```

### Просмотр полной истории движения почтового отправления
### GET
```
/mailing/full?uuid={UUID}
```

### Просмотр статуса почтового отправления
### GET
```
/mailing/status?uuid={UUID}
```
## Схема данных
Выделено 2 сущности по заданию - Почтовое отправление и Почтовое отделение:

![Схема данных](https://github.com/JGorsh/SkyEngTest/blob/3c819f7b2f11a9ef0aab8f86f8f82334f9390ac6/Screenshot%202023-08-24%20094511.png)

## Покрытие тестами
![](https://github.com/JGorsh/SkyEngTest/blob/d6c8eac625815228dbee7dae64ff834a3aa5f7de/Tests.png)

## Приложение собрано в war-архив, протестированно и работает с помощью Apache Tomcat 9 
[WAR-АРХИВ-В-КОРНЕ-ПРОЕКТА](https://github.com/JGorsh/SkyEngTest/blob/2363e013aed8a7fb8fdb3c62cfd3643d91211b79/SkyEngTest.war)

![](https://github.com/JGorsh/SkyEngTest/blob/2363e013aed8a7fb8fdb3c62cfd3643d91211b79/Tomcat.png)

## Технологии
Стек: Spring
Способ передачи данных: JSON
СУБД: H2
Реализация ORM: Hibernate
Система сборки: Maven
