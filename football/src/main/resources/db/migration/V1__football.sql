create table players (id bigint not null auto_increment, date_of_birth date, name varchar(255), position integer, team_id bigint, primary key (id));
create table teams (id bigint not null auto_increment, name varchar(255), primary key (id));
alter table players add constraint FK_PT foreign key (team_id) references teams (id);