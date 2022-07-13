Для того что бы развернуть приложение на heroku

1. Оформить модуль приложения как отдельный репозиторий в git
2. Зарегистрироваться на heroku https://heroku.com/login
3. Создать приложение
4. Добавить аддон postgreSQL
5. Сохранить настройки подключения к БД
6. Через pgAdmin создать и заполнить схему БД
7. Скачать Heroku CLI (https://devcenter.heroku.com/articles/heroku-cli)
8. heroku login
9. heroku create
10. heroku git:remote -a web-project-embedded
11. Теперь можно делать пуш данных на хероку git push heroku web-project-embedded