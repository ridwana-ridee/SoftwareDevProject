use employeeData;
CREATE TABLE employee (
    empID INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    division VARCHAR(100) NOT NULL,
    jobTitle VARCHAR(100) NOT NULL,
    ssn CHAR(9) NOT NULL UNIQUE,
    salary DECIMAL(10,2) NOT NULL
);

CREATE TABLE payStatement (
    payID INT AUTO_INCREMENT PRIMARY KEY,
    empID INT NOT NULL,
    date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (empID) REFERENCES employee(empID) ON DELETE CASCADE
);
