<h1 align="center"> JDBC Student Database Project </h1>

![Java](https://img.shields.io/badge/Java-17-blue)
![MySQL](https://img.shields.io/badge/MySQL-8-orange)
 
This project demonstrates **JDBC (Java Database Connectivity)** by connecting a Java program to a **MySQL database**.  
It provides basic operations like inserting, updating, deleting, and retrieving student records.  

---

##  Features : 
- Connects Java with MySQL database  
- Console-based menu for CRUD operations  
- Clean separation of database logic  
- Easy to set up and run  

---

##  Tech Stack :
- **Java** (JDK 17 or later)  
- **JDBC API**  
- **MySQL** (8.x recommended)  
- **MySQL Connector/J** (JDBC driver)  

---

## 📂 Project Structure :


<img width="554" height="313" alt="Screenshot 2025-09-05 182335" src="https://github.com/user-attachments/assets/ae5dc7d1-0c97-4bb7-9fdb-739820540eea" />

---

##  Setup Instructions : 

### 1. Clone the Repository :  
```bash
git clone https://github.com/Govindu07/Java-Jdbc.git
cd Java-Jdbc
```

### 2. Add MySQL Connector JAR :

- Download [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/?utm_source=chatgpt.com)
 and place it inside the library/ folder.

### 3. Configure Database :

- Create a MySQL database:
```bash
CREATE DATABASE jdbc_test;
USE jdbc_test;

CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    age INT,
    course VARCHAR(50)
);
```

### 4. Update StudentDataBase.java with your DB credentials:
```bash
static final String URL = "jdbc:mysql://localhost:3306/jdbc_test";
static final String USER = "root";
static final String PASSWORD = "5007";
```

### 5. Compile & Run :
```bash
javac -cp "library/*" src/StudentDataBase.java
java -cp "library/*:src" StudentDataBase
```

---

## Example Output :
```bash
Connected to Database : student_db 

**** Student Management Menu ****
1. Add Student
2. View All Students
3. Update Student 
4. Delete Student
5. Update newly  created columns in table
6. Search Student
7. Exit 
Enter your choice: 
2

 *** Student Records ***
1 | Alice | alice@example.com | 21 | Hyderabad | 2003-05-10 | OU
2 | govi | govi@gmail.com | 23 | kurnool | 2005-08-24 | svce

**** Student Management Menu ****
1. Add Student
2. View All Students
3. Update Student 
4. Delete Student
5. Update newly  created columns in table
6. Search Student
7. Exit 
Enter your choice:

```

---





