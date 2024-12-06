Employee Management System

The Employee Management System is a Java-based application designed to help organizations manage employee records and payroll efficiently. It provides features like CRUD operations for employees, salary updates, and report generation through a console-based interface.

Features

Employee Management:
Add, search, update, and delete employee records.
Manage key details like name, division, job title, SSN, and salary.
Salary Updates:
Adjust salaries by a percentage within specified ranges.
Reports:
Generate detailed employee reports with pay statements.
Calculate total pay by job title or division.
Pay Statement Management:
Link employee pay statements and cascade delete when an employee is removed.
Technologies Used

Programming Language: Java
Database: MySQL
Development Framework: JDBC
Build Tool: Maven (optional)
Setup and Installation

Prerequisites
Install Java Development Kit (JDK) (version 8 or higher).
Install MySQL and configure the database.
Database Configuration
Create a database named employeeData.
Run the createTables.sql file to create the necessary tables.
Run the populateTables.sql file to populate the database with sample data.
Project Setup
Clone the repository:
git clone https://github.com/your-username/employee-management-system.git
Navigate to the project directory:
cd employee-management-system
Compile the Java files:
javac -cp .:mysql-connector-java.jar *.java
Run the application:
java ConsoleUX
Usage

Main Menu
Choose an option by entering the corresponding number.
Follow on-screen prompts to perform operations.
Example main menu:

--- Employee Management System ---
1. Add Employee
2. Search Employee
3. Delete Employee
4. Generate Reports
5. Update Employee Data
6. Update Salaries by Percentage
7. Exit
Enter your choice:
Example Operations
Add Employee: Enter details like name, division, job title, SSN, and salary to create a new record.
Search Employee: Look up employees by ID, name, or SSN.
Generate Reports: View all employees with pay statements or calculate total pay by division or job title.
File Structure

createTables.sql - SQL script to create database tables.
populateTables.sql - SQL script to populate tables with sample data.
EmployeeManager.java - Handles core employee operations.
ReportGenerator.java - Generates reports for employees and salaries.
ConsoleUX.java - User interface for interacting with the system.
Future Enhancements

Implement a graphical user interface (GUI).
Add role-based access control for user authentication.
Introduce advanced reporting and data visualization features.
Encrypt sensitive data like SSNs for enhanced security.
Contributing

Contributions are welcome! Please follow these steps:

Fork the repository.
Create a feature branch:
git checkout -b feature-name
Commit your changes:
git commit -m "Add feature-name"
Push to the branch:
git push origin feature-name
Open a pull request.
License

This project is licensed under the MIT License.

Contact

For questions or suggestions, feel free to contact me:
[Your Name]
[Your Email]
