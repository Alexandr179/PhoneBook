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
sslMode:        always
sslFactory:     org.postgresql.ssl.NonValidatingFactory



------------------------ HEROKU sleaping -----------------------

Согласно документации, Dyno hours — это, по сути, время работы вашего приложения/приложений, 
выраженное в часах/месяц. Сразу после регистрации бесплатного аккаунта вам дается 550 часов в месяц. 
Посему привязываем кредитную карту в Account settings — Billing и получаем еще 450 бесплатных часов (32 часа в сутки ). 

В вышеупомянутом документе указано также о "засыпании" (dyno sleeping) приложения при отсутствии активности на протяжении 30 минут.

Использовать Heroku Newrelic addon Данный плагин предназначен для мониторинга и уведомления о падениях сайта, 
но как полезный "побочный эффект" он не даст заснуть приложению.

Чтобы установить addon, нужно перейти по данной ссылке и нажать кнопку "Install New Relic APM". 
В открывшемся окне нужно выбрать тарифный план и название приложения, к которому будет применен данный addon.

После чего нажать кнопку "Provision add-on".
После установки New Relic станет доступен на странице настройки приложения в списке установленных аддонов.

Клацаем на ссылку и попадаем на страницу настроек дополнения.
Переходим на вкладку "Synthetics" и нажимаем кнопку "Add new".

В открывшемся окне в разделе "Enter the details" указываем произвольное название монитора и адрес сайта, 
который будем мониторить. Выбираем локацию, откуда будет сайт проверяться в "Select monitoring locations".

Периодичность проверки указываем в "Set the shedule" (15 минут) и email для уведомлений в "Get notified".

После всех настроек не забываем нажать кнопку "Create monitor".