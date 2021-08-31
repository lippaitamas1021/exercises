create table dog (
                     id bigint(10) not null auto_increment,
                     name varchar(255),
                     breed varchar(255),
                     age bigint(10),
                     fav_toy varchar(255),
                     owner_id bigint(10),
                     primary key (id));

create table owner (
                    id bigint(10) not null auto_increment,
                    name varchar(255),
                    primary key (id));

alter table dog add constraint FK_Dog foreign key (owner_id) references owner (id);
