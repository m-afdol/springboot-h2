CREATE TABLE users
(
	id IDENTITY primary key,
	name varchar(50) not null,  
    social_security_number varchar(5) not null,
    date_of_birth date not null, 
    date_created datetime not null, 
    date_updated datetime not null, 
    updated_by varchar(50) not null, 
    created_by varchar(50) not null, 
    is_deleted boolean
);