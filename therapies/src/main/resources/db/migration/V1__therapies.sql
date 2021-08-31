create table therapy (
                        id bigint(10) not null auto_increment,
                        therapist varchar(255),
                        place varchar(255),
                        date varchar(255),
                        primary key (id));

create table participant (
                        id bigint(10) not null auto_increment,
                        name varchar(255),
                        therapy_id bigint(10),
                        primary key (id));
alter table participant add constraint FK_Participant foreign key (therapy_id) references therapy (id);
