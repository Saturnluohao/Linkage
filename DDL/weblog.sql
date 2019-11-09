create schema linkage collate utf8mb4_0900_ai_ci;
CREATE TABLE `weblog` (
  `blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `poster_id` int(11) NOT NULL,
  `text` text NOT NULL,
  `picture_num` int(2) DEFAULT NULL,
  `video_num` int(2) DEFAULT NULL,
  PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table api
(
    id int auto_increment
        primary key,
    pattern varchar(255) not null
);

create table message
(
    id int auto_increment
        primary key,
    name varchar(50) not null,
    content text null,
    `to` varchar(50) not null,
    status int null
);

create table role
(
    id int auto_increment
        primary key,
    name varchar(32) not null
);

create table api_role
(
    id int auto_increment
        primary key,
    aid int not null,
    rid int not null,
    constraint api_role_api_id_fk
        foreign key (id) references api (id)
            on update cascade on delete cascade,
    constraint api_role_role_id_fk
        foreign key (id) references role (id)
            on update cascade on delete cascade
);

create table user
(
    id int auto_increment,
    username varchar(32) not null,
    password varchar(255) not null,
    enabled tinyint(1) not null,
    locked tinyint(1) not null,
    phoneNumber varchar(30) not null,
    description text null,
    sex smallint null,
    constraint user_id_uindex
        unique (id),
    constraint user_username_uindex
        unique (username)
);

create table friend
(
    id int auto_increment
        primary key,
    my_username varchar(32) null,
    friend_username varchar(32) null,
    constraint friend_user_username_fk
        foreign key (my_username) references user (username)
            on update cascade on delete cascade,
    constraint friend_user_username_fk_2
        foreign key (friend_username) references user (username)
            on update cascade on delete cascade
);

create index user_phoneNumber_index
    on user (phoneNumber);

alter table user
    add primary key (id);

create table user_role
(
    id int auto_increment
        primary key,
    uid int not null,
    rid int not null,
    constraint user_role_role_id_fk
        foreign key (rid) references role (id)
            on update cascade on delete cascade,
    constraint user_role_user_id_fk
        foreign key (uid) references user (id)
            on update cascade on delete cascade
);

