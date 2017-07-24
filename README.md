# dropwizard-rest
Серверная часть приложения PhoneBook, созданная на основе Dropwizard.
Запуск приложения.
1. mvn package сборка приложения
2. Перед запуском приложения создается переменная среды окружения DATABASE_URL вида
postgres://loginDB:passwordDB@addressDB:host/nameOfDB
3. Запуск приложения в терминале
DATABASE_URL=postgres://loginDB:passwordDB@addressDB:host/nameOfDB  java -jar target/dropwizard-1.0-SNAPSHOT.jar server config.yaml

где server - запуск сервера, config.yaml - конфигурационный файл.
Так же возможно использование вместо аргемента server аргумента db migrate для миграции изменений в бд.


