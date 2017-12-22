Copiar o arquivo settings.xml para a pasta ".m2/"

Variaveis de Ambiente 
	- Variaveis de Sistema
		ADD: MARIADB_HOME / "C:\Program Files\MariaDB 10.2"
		UPDATE: PATH: (add) %MARIADB_HOME%\bin

Database
	create database poc;
	use poc;
	create table message (id int not null auto_increment, message varchar(3000) not null, created timestamp not null default current_timestamp, constraint pk_message primary key (id));
	create table notification (id int not null auto_increment, notification varchar(3000) not null, created timestamp not null default current_timestamp, constraint pk_notification primary key (id));
	