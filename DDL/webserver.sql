create schema linkage collate utf8mb4_0900_ai_ci;

create table role
(
    id int auto_increment
        primary key,
    name varchar(32) not null
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
    lastLogIn timestamp default CURRENT_TIMESTAMP not null,
    constraint user_id_uindex
        unique (id),
    constraint user_username_uindex
        unique (username)
);

create table add_friend_request
(
    id int auto_increment
        primary key,
    username varchar(32) not null,
    targetName varchar(32) not null,
    selfIntro text null,
    readStatus smallint not null,
    acceptStatus smallint null,
    replyStatus smallint null,
    requestTime timestamp default CURRENT_TIMESTAMP not null,
    constraint add_friend_request_user_username_fk
        foreign key (username) references user (username)
            on update cascade on delete cascade,
    constraint add_friend_request_user_username_fk_2
        foreign key (targetName) references user (username)
            on update cascade on delete cascade
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

create table message
(
    id int auto_increment
        primary key,
    name varchar(32) not null,
    content text null,
    `to` varchar(32) not null,
    status int null,
    sendTime timestamp default CURRENT_TIMESTAMP not null,
    constraint message_user_username_fk
        foreign key (name) references user (username)
            on update cascade on delete cascade,
    constraint message_user_username_fk_2
        foreign key (`to`) references user (username)
            on update cascade on delete cascade
);

create index user_phoneNumber_index
    on user (phoneNumber);

alter table user
    add primary key (id);

create definer = root@localhost trigger UserLogInSetAllRead
    after UPDATE on user
    for each row
begin
    update message set status=1 where (sendTime between OLD.lastLogIn and NEW.lastLogIn) and (`to`=NEW.username);
    update add_friend_request set readStatus=1 where (requestTime between OLD.lastLogIn and NEW.lastLogIn)
                                                 and (username=NEW.username or targetName = NEW.username);
end;

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


create trigger UserLogInSetAllRead after update on user for each row
begin
    update message set status=1 where (sendTime between OLD.lastLogIn and NEW.lastLogIn) and (`to`=NEW.username);
    update add_friend_request set readStatus=1 where (requestTime between OLD.lastLogIn and NEW.lastLogIn)
                                                 and (username=NEW.username or targetName = NEW.username);
end;
