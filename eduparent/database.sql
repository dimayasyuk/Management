create table user(
	id bigint(20) not null AUTO_INCREMENT,
	name varchar(20) default null,
	second_name varchar(20) default null,
	father_name varchar(20) default null,
	gender enum('M','F') default null,
	birthday date default null,
	adress varchar(30) default null,
	email varchar(45) default null,
	role_id bigint(20),
	login varchar(20) default null,
	password varchar(20) default null
	primary key(id),
	constraint foreign key(role_id) 
	references role(id)
	)ENGINE=InnoDB AUTO_INCREMENT=1

create table role(
	id bigint(20) not null AUTO_INCREMENT,
	name enum('Manager','Admin','Tester','Developer') default null
)ENGINE=InnoDB AUTO_INCREMENT=1


create table project(
	id bigint(20) not null AUTO_INCREMENT,
	name varchar(20) default null,
	code int(10) default null,
	status enum('Open','In progress','closed') default null,
	priority enum('blocker','critical','major','normal','minor') default null,
	created datetime default null,
	updated datetime default null,
	due_date datetime default null,
	estimation int(10) default null,
	description text default null,
	primary key(id)

)ENGINE=InnoDB AUTO_INCREMENT=1

create table task(
	id bigint(20) not null AUTO_INCREMENT,
	project_id bigint(20) not null,
	user_id bigint(20),
	name varchar(20) default null,
	code int(10) default null,
	status enum('Open','In progress','Closed','Reade for Test') default null,
	priority enum('blocker','critical','major','normal','minor') default null,
	created datetime default null,
	updated datetime default null,
	due_date datetime default null,
	estimation int(10) default null,
	description text default null,
	primary key(id),
	constraint foreign key(project_id)
	references project(id)
	constraint foreign key(user_id)
	references user(id)
)ENGINE=InnoDB AUTO_INCREMENT=1