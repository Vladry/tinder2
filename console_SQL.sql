create table tinder.users
(
    id   serial
        constraint users_pk
            primary key,
    name varchar(15) not null
);

create unique index users_id_uindex
    on tinder.users (id);

ALTER TABLE tinder.users
add column avatar VARCHAR(150);

UPDATE tinder.users SET avatar = 'https://res.cloudinary.com/vladry/image/upload/v1628498611/vlad_shrunk/guitar_and_cat_painting_tbjpdl.jpg'
where id = 3;


create table tinder.messages
(
    id serial
        constraint messages_pk
            primary key,
    message    varchar,
    from_id    int not null,
    to_id      int not null,
    date       timestamp
);

SELECT * from tinder.messages;
INSERT INTO tinder.messages VALUES
                                   (1, 'hi, im your first message', 1, 2, now()),
                                   (2, 'yes, I see your first message', 2, 1, now()),
                                   (3, 'how are you?', 3, 4, now()),
                                   (4, 'Im great', 4, 3, now());

CREATE TABLE tinder.likes (
    id serial
                          constraint likes_pk
                          primary key,
   liked_by INT not null,
   liked_who INT not null

);
INSERT INTO tinder.likes (liked_by, liked_who) VALUES
(1,2),
(2,1),
(3,4);
SELECT * FROM tinder.likes;

SELECT * from tinder.users;