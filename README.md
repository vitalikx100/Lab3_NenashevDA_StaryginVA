Практическая работа №3 RESTful веб-сервис

Выполнили студенты 6132-010402D Ненашев Данила, Старыгин Виталий

```
IntelliJ IDEA Ultimate

OpenJDK 17.0.15
```

```
Предметная область - система управления задачами

Сущности: пользователь (User), задача (Task)
```

СУБД PostgreSQL

Скрипт для создания и заполнения базы данных:
```
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    age INTEGER NOT NULL CHECK (age >= 18 AND age <= 75)
);

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    created_date DATE NOT NULL DEFAULT CURRENT_DATE,
    completed BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO users (name, email, age) VALUES 
('Старыгин Виталий', 'vs@mail.ru', 22),
('Ненашев Данила', 'nd@gmail.com', 22),
('Иванов Иван', 'ii@yandex.ru', 18),
('Петров Петр', 'pp@inbox.ru', 75);

INSERT INTO tasks (title, user_id, created_date, completed) VALUES 
('Написать код программы', 1, '2025-10-20', false),
('Протестировать код программы', 2, '2025-10-21', false),
('Написать фидбек', 2, '2025-10-22', false),
('Написать README', 3, '2025-10-23', false);
```

# Результаты запросов в Postman:

Получение всех user:
<img width="1477" height="878" alt="image" src="https://github.com/user-attachments/assets/10cfd1cf-c2ed-45b5-8095-8ffe5ca310fc" />


<img width="1472" height="726" alt="image" src="https://github.com/user-attachments/assets/adbd586b-0718-4779-a1db-546e45f0111d" />

Получение user'а по id:
<img width="1470" height="709" alt="image" src="https://github.com/user-attachments/assets/9a98f754-1c95-45ae-bcac-c5e20f5bd6e3" />


<img width="1469" height="453" alt="image" src="https://github.com/user-attachments/assets/41083a2c-d414-4b9b-b97c-bd41027dc725" />

Добавление нового user'а:
<img width="1479" height="448" alt="image" src="https://github.com/user-attachments/assets/389364fa-2d68-497a-8b4a-2345133d9812" />


<img width="1481" height="479" alt="image" src="https://github.com/user-attachments/assets/ec7892bb-38fb-45fb-88e1-edce3c80cec5" />

Изменение user'а:
<img width="1476" height="607" alt="image" src="https://github.com/user-attachments/assets/af46806e-11ab-4102-956f-6fa51ff02430" />


<img width="1477" height="478" alt="image" src="https://github.com/user-attachments/assets/d952c1c8-518e-489e-995f-9fabe8971a2e" />

Удаление user'а:
<img width="1478" height="359" alt="image" src="https://github.com/user-attachments/assets/f60fe2e7-8662-4e60-b2d7-b7f9fab9f8f9" />


Все тоже самое работает и с task!

# В браузере:

Просмотр всех task:

<img width="656" height="276" alt="image" src="https://github.com/user-attachments/assets/d8de33d0-6574-4a2d-b656-03f83520d85b" />

Просмотр task'а по id:

<img width="435" height="338" alt="image" src="https://github.com/user-attachments/assets/28220241-cd76-4455-bded-f99e88759806" />

Просмотр всех user:

<img width="694" height="318" alt="image" src="https://github.com/user-attachments/assets/c1b3e21c-cfa3-4a41-bf19-65904e59630e" />

Просмотр user'а по id:

<img width="453" height="303" alt="image" src="https://github.com/user-attachments/assets/6748357d-e58e-4f23-8040-a62d43e0b3c8" />
