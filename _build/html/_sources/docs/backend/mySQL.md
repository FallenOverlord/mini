# mySQL Basics

mySQL is a relational database management system.  
It is a software that manages databases.

## Introduction

There is always something that should uniquely identify a row in the table(database).

Columns represents attributes.  
Primary Key: unique attributes for each row.  
Segarate Key: a primary key that has no real-world implications.  
Natural Key: a real-world attribute that is used as the primary key.  
Foreign Key: Primary keys from another table. The way multiple tables connect.  
Composite Key: when there is no sigle attribut that uniquely define a row, u need the bondle of two attributes that is unique for each row.  
You can use two foriegn keys as a composite key.  

### Intro to SQL
A STANDARD language for interacting with RELATIONAL databases.  
Queries: request made to DB management sys for specific info  

SQL = Data (Query + Definition + Control + Manipulation) Language.  


```sql
create database db1;

CREATE TABLE student ();

CREATE TABLE student (
    student_id INT PRIMARY KEY,
    name VARCHAR(20),
    major VARCHAR(20)
);

DESCRIBE student;

DROP TABLE student;

ALTER TABLE student ADD gpa DECIMAL(10,4);

ALTER TABLE student DROP COLUMN gpa;



```

SQL Datatypes
```sql
INT
DECIMAL(M, N)
VARCHAR(l)
BLOB
DATE
TIMESTAMP

```



