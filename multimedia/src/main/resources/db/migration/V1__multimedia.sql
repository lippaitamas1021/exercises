create table book (
                id bigint(10) not null auto_increment,
                author varchar(255),
                title varchar(255),
                date varchar(255),
                primary key (id));

create table movie (
                id bigint(10) not null auto_increment,
                title varchar(255),
                director varchar(255),
                studio varchar(255),
                primary key (id));

create table music (
                id bigint(10) not null auto_increment,
                performer varchar(255),
                title varchar(255),
                genre varchar(255),
                primary key (id));
