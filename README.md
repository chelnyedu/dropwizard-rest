# dropwizard-rest
Серверная часть приложения PhoneBook, созданная на основе Dropwizard.
Запуск приложения.
1. mvn package сборка приложения
2. Перед запуском приложения создается переменная среды окружения "DATABASE_URL" вида
> postgres://loginDB:passwordDB@addressDB:host/nameOfDB

* `username` - логин пользователя базы данных
* `password` - пароль пользователя базы данных
* `host` - вид хоста
* `port` - номер порта
* `dbName` - название базы данных


3. Запуск приложения в терминале
> DATABASE_URL=postgres://loginDB:passwordDB@addressDB:host/nameOfDB  java -jar target/dropwizard-1.0-SNAPSHOT.jar server config.yaml

где server - запуск сервера, config.yaml - конфигурационный файл.
Так же перед первым запуском необходимл использование вместо аргумента server
аргумента db migrate для миграции изменений в бд.

Пример корректной переменной.
>DATABASE_URL=postgres://qwerty:1234@localhost:5432/phonebook


