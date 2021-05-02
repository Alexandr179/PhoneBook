# How to use API

- clone Api (GitHub) на локальную машину
- запускаем Api (из под IDE или из bash)
- отслеживаем прохождение тестов
- тестируем работу операций (используем requests.http, как аналог curl)
- общий обзор API: http://localhost/swagger-ui.html



>В приложении для демонстрации задействован как Hibernate, так и наполнение DB скриптами.
Наличие Dto является необходимым и обязательным (упрощающим работу с Json и выводом данных) условием.

>В приложении расширена функциональность model, добавлена security (token, byCrypt), статус тлф.записи и время
>тлф.книга User-а может содержать не один ему принадлежащий тлф.номер            
>            - для последующего масштабирования Api.

>В ТЗ указано на необходимость реализации Api без DB. Поставлена embedded база (переведено на H2), 
 возможен запуск без указания доп.credentials и конфигурации DB типа postrges.



------------- .yaml  файл (CI/CD on heroku) ---------------------------------

name: Heroku Deployment

on:
  push:
    branches: [ master ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v2
    - name: Deploy to Heroku
      uses: AkhileshNS/heroku-deploy@v3.8.8
      with:
        heroku_api_key: ${{secrets.HEROKU_API_KEY}}
        heroku_email: ${{secrets.HEROKU_EMAIL}}
        heroku_app_name: ${{secrets.HEROKU_APP}}


------------- credentials from Heroku_DB  -------------------

Host:           ec2-54-73-147-133.eu-west-1.compute.amazonaws.com
User:           aimwhbuehbfumg
Password:       53c2c4fea9449326ce5aeb64b9c9e86d4a1d13b6ccf7e148b14032bec146d81a
Database:       da6bn353pqbc21
Port:           5432

        ..advanced:
ssl:            true
sslFactory:     org.postgresql.ssl.NonValidatingFactory

