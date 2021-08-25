create table dogs (
                id bigint not null auto_increment,
                name varchar(255),
                breed varchar(255),
                age bigint,
                owner_id bigint,
                primary key (id));

create table owners (
                id bigint not null auto_increment,
                name varchar(255),
                primary key (id));