import java.sql.*;
import java.util.Scanner;
public class employeeManager {
    
    private static final String URL = "jdbc:mysql://localhost:3306/employeeData"; // Replace with your DB name
    private static final String USER = "root"; // Replace with your username
    private static final String PASSWORD = ""; // Replace with your password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    protected static void addEmployee(Scanner scanner) {
        try (Connection conn = getConnection()) {
            System.out.println("\n--- Add New Employee ---");
    
            System.out.print("Enter First Name: ");
            String firstName = scanner.next();
    
            System.out.print("Enter Last Name: ");
            String lastName = scanner.next();
    
            System.out.print("Enter Division: ");
            String division = scanner.next();
    
            System.out.print("Enter Job Title: ");
            scanner.nextLine(); // Clear the newline left by next()
            String jobTitle = scanner.nextLine();
    
            System.out.print("Enter SSN (9 digits): ");
            String ssn = scanner.next(); // This should now work correctly
    
            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();
    
            String sql = "INSERT INTO employee (first_name, last_name, division, jobTitle, ssn, salary) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, division);
                stmt.setString(4, jobTitle);
                stmt.setString(5, ssn);
                stmt.setDouble(6, salary);
    
                stmt.executeUpdate();
                System.out.println("Employee added successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
    }
    

    protected static void searchEmployee(Scanner scanner) {
        try (Connection conn = getConnection()) {
            System.out.println("\n--- Search Employee ---");
            System.out.print("Search by (1: ID, 2: Name, 3: SSN): ");
            int searchType = scanner.nextInt();
            String sql = "";
            String searchValue;

            switch (searchType) {
                case 1 -> {
                    System.out.print("Enter Employee ID: ");
                    int empID = scanner.nextInt();
                    sql = "SELECT * FROM employee WHERE empID = ?";
                    searchValue = String.valueOf(empID);
                }
                case 2 -> {
                    System.out.print("Enter Name (First or Last): ");
                    searchValue = scanner.next();
                    sql = "SELECT * FROM employee WHERE first_name = ? OR last_name = ?";
                }
                case 3 -> {
                    System.out.print("Enter SSN: ");
                    searchValue = scanner.next();
                    sql = "SELECT * FROM employee WHERE ssn = ?";
                }
                default -> {
                    System.out.println("Invalid search type.");
                    return;
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, searchValue);
                if (searchType == 2) stmt.setString(2, searchValue);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("Employee ID: " + rs.getInt("empID"));
                        System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                        System.out.println("Division: " + rs.getString("division"));
                        System.out.println("Job Title: " + rs.getString("jobTitle"));
                        System.out.println("SSN: " + rs.getString("ssn"));
                        System.out.println("Salary: " + rs.getDouble("salary"));
                        System.out.println("---");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching employee: " + e.getMessage());
        }
    }

    protected static void deleteEmployee(Scanner scanner) {
        try (Connection conn = getConnection()) {
            System.out.println("\n--- Delete Employee ---");
            System.out.print("Enter Employee ID to delete: ");
            int empID = scanner.nextInt();

            String query = "SELECT * FROM employee WHERE empID = ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(query)) {
                selectStmt.setInt(1, empID);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Are you sure you want to delete this employee?");
                        System.out.println("Employee ID: " + rs.getInt("empID"));
                        System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                        System.out.print("Confirm (yes/no): ");
                        String confirm = scanner.next();

                        if (confirm.equalsIgnoreCase("yes")) {
                            String deleteSql = "DELETE FROM employee WHERE empID = ?";
                            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                                deleteStmt.setInt(1, empID);
                                deleteStmt.executeUpdate();
                                System.out.println("Employee deleted successfully.");
                            }
                        } else {
                            System.out.println("Delete operation canceled.");
                        }
                    } else {
                        System.out.println("Employee not found.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }
    protected static void updateEmployeeData(Scanner scanner) {
        try (Connection conn = getConnection()) {
            System.out.print("\nEnter the Employee ID of the employee you want to update: ");
            int empID = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            System.out.println("\nWhich field would you like to update?");
            System.out.println("1. First Name");
            System.out.println("2. Last Name");
            System.out.println("3. Division");
            System.out.println("4. Job Title");
            System.out.println("5. SSN");
            System.out.println("6. Salary");
    
            System.out.print("Enter your choice (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            String fieldToUpdate = switch (choice) {
                case 1 -> "first_name";
                case 2 -> "last_name";
                case 3 -> "division";
                case 4 -> "jobTitle";
                case 5 -> "ssn";
                case 6 -> "salary";
                default -> "Invalid choice!";

            };
    
            System.out.print("Enter the new value: ");
            String newValue = scanner.nextLine();
    
            String sql = "UPDATE employee SET " + fieldToUpdate + " = ? WHERE empID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                if (choice == 6) {
                    stmt.setDouble(1, Double.parseDouble(newValue));
                } else {
                    stmt.setString(1, newValue);
                }
                stmt.setInt(2, empID);
    
                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected > 0
                        ? "Employee data updated successfully!"
                        : "No employee found with the specified ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating employee data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please ensure numerical values for salary.");
        }
    }
    protected static void updateSalariesByPercentage(Scanner scanner) {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false); // Enable manual transaction handling
    
            System.out.print("\nEnter the lower bound of the salary range: ");
            double lowerBound = scanner.nextDouble();
    
            System.out.print("Enter the upper bound of the salary range: ");
            double upperBound = scanner.nextDouble();
    
            System.out.print("Enter the percentage increase (e.g., 3.2 for 3.2%): ");
            double percentage = scanner.nextDouble();
    
            String updateSql = "UPDATE employee SET salary = salary + (salary * ? / 100) WHERE salary >= ? AND salary < ?";
            String selectSql = "SELECT empID, first_name, last_name, salary FROM employee WHERE salary >= ? AND salary < ?";
    
            try (
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                PreparedStatement selectStmt = conn.prepareStatement(selectSql)
            ) {
                // Perform the salary update
                updateStmt.setDouble(1, percentage);
                updateStmt.setDouble(2, lowerBound);
                updateStmt.setDouble(3, upperBound);
                int rowsAffected = updateStmt.executeUpdate();
    
                conn.commit(); // Commit the update
    
                if (rowsAffected == 0) {
                    System.out.println("No employees found in the specified salary range.");
                    return; // Exit if no rows are affected
                }
    
                // Retrieve updated rows
                selectStmt.setDouble(1, lowerBound);
                selectStmt.setDouble(2, upperBound);
                ResultSet rs = selectStmt.executeQuery();
    
                System.out.printf("\n%d employees had their salaries updated by %.2f%%. The affected rows are:%n", rowsAffected, percentage);
                System.out.printf("%-5s %-15s %-15s %-10s%n", "empID", "First Name", "Last Name", "New Salary");
    
                while (rs.next()) {
                    int empID = rs.getInt("empID");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    double newSalary = rs.getDouble("salary");
                    System.out.printf("%-5d %-15s %-15s %-10.2f%n", empID, firstName, lastName, newSalary);
                }
            } catch (SQLException e) {
                conn.rollback(); // Rollback on error
                System.err.println("SQL Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
    
}
