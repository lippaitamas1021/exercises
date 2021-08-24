create table players (
                         id bigint(10) not null auto_increment,
                         name varchar(255),
                         date_of_birth varchar(255),
                         position varchar(255),
                         team varchar(255),
                         team_id bigint(10),
                         primary key (id));