create table client (
	id bigint not null auto_increment,
    `name` varchar(60) not null,
    document varchar(30) not null,
	phone varchar(30) not null,
	birth varchar(10) not null,
	email varchar(60) not null,
	`password` varchar(60) not null,
    
    primary key (id)
);