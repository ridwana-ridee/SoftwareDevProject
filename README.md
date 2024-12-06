# Employee Management System

## Overview
The **Employee Management System** is a console-based application designed to streamline employee management processes. It allows users to add, search, update, and delete employee records, manage salaries, and generate detailed reports. The system uses a MySQL database for data persistence and Java for its implementation.

---

## Features
### **Employee Management**
- Add new employees with details such as name, division, job title, SSN, and salary.
- Search for employees by ID, name, or SSN.
- Update employee details or salary information.
- Delete employee records with cascading removal of related pay statements.

### **Salary Management**
- Update employee salaries within a specified range by a percentage increase.

### **Report Generation**
- Generate detailed reports for:
  - All employees with pay statements.
  - Total pay by division or job title.

---

## Installation and Setup
### Prerequisites
1. **Java Development Kit (JDK)** 8 or above.
2. **MySQL Server** installed and running.
3. A **MySQL Client** (e.g., MySQL Workbench) for database setup.

### Steps to Run the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/<username>/employee-management-system.git
   cd employee-management-system

