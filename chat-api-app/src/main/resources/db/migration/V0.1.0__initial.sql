CREATE TABLE chat_message (
       id bigint primary key auto_increment not null,
       username varchar(30) not null,
       ts TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
       message varchar(250) not null
);
