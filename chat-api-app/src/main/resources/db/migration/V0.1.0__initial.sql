CREATE TABLE user (
       id bigint primary key auto_increment not null,
       name varchar(50) not null
);

CREATE TABLE room (
       id bigint primary key auto_increment not null,
       name varchar(50) not null
);

CREATE TABLE chat_message (
       id bigint primary key auto_increment not null,
       user_id bigint not null,
       room_id bigint not null,
       ts TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
       message varchar(250) not null,
       CONSTRAINT fk_user FOREIGN KEY (user_id)
         REFERENCES user(id) 
	   ON DELETE CASCADE
	   ON UPDATE CASCADE,
       CONSTRAINT fk_room FOREIGN KEY (room_id)
         REFERENCES room(id) 
	   ON DELETE CASCADE
	   ON UPDATE CASCADE 
	   
);
