create database university default character set utf8 default collate utf8_general_ci;

/* create department table in university*/
create table department (
	deptID varchar(10) primary key,
	deptName varchar (100),
	location varchar (100)

);


/*create registeration type table in university*/
create table registeration (
	regID varchar (5) primary key ,
	regName varchar(10),
	fee int (10)
);
/*create peculiarity table in university*/

create table peculiarity (
	pecID varchar (5) primary key ,
	pecName varchar (50),
	discount int (10)
);

/*create table student in university*/
create table student (
	studentID varchar (15) primary key,
	universityID int (10),
	firstName varchar(20),
	middelName varchar(20),
	lastName varchar(20),
	motherName varchar(20),
	address varchar (100),
	hiringDate date,
	fee int (10),
	deptID varchar(10),
	regID varchar (5),
	pecID varchar (5),
	foreign key (pecID) references peculiarity(pecID),
	foreign key (regID) references registeration(regID),
	foreign key (deptID) references department(deptID),
	unique (universityID,deptID)
	
);
/* create certificate table in university*/
create table certificate (
	cerID varchar(10),
	studentID varchar(15),
	cerType varchar(50),
	takeDate date,
	degreeRate varchar(5),
	cerLanguage varchar (10),
	foreign key (studentID) references student (studentID),
	primary key (cerID,studentID)
);
/* create student phone table*/
create table studentPhones (
    studentID varchar (15),
    phone varchar (11),
    FOREIGN key (studentID) REFERENCES student (studentID),
    PRIMARY key (studentID,phone)
    );
	