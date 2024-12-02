import java.sql.*;
import java.util.Scanner;

public class reportGenerator {

     private static final String URL = "jdbc:mysql://localhost:3306/employeeData"; // Replace with your DB name
    private static final String USER = "root"; // Replace with your username
    private static final String PASSWORD = ""; // Replace with your password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }



    protected static void generateAllEmployeeReport() {
        try (Connection conn = getConnection()) {
            System.out.println("\n--- All Employees with Pay Statements ---");
            
            String sql = """
                SELECT e.empID, e.first_name, e.last_name, e.division, e.jobTitle, e.salary, ps.date, ps.amount 
                FROM employee e
                LEFT JOIN payStatement ps ON e.empID = ps.empID
                ORDER BY e.empID, ps.date;
            """;
    
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
    
                int currentEmpID = -1;
                while (rs.next()) {
                    int empID = rs.getInt("empID");
                    
                    // Print employee details only when we encounter a new employee ID
                    if (empID != currentEmpID) {
                        if (currentEmpID != -1) {
                            System.out.println("---------------------------");
                        }
                        currentEmpID = empID;
                        
                        System.out.println("Employee ID: " + empID);
                        System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                        System.out.println("Division: " + rs.getString("division"));
                        System.out.println("Job Title: " + rs.getString("jobTitle"));
                        System.out.println("Salary: " + rs.getDouble("salary"));
                        System.out.println("Pay Statements:");
                    }
                    
                    // Print pay statements (if available)
                    Date payDate = rs.getDate("date");
                    if (payDate != null) {
                        System.out.println("  - Date: " + payDate + ", Amount: " + rs.getDouble("amount"));
                    } else {
                        System.out.println("  - No pay statements available.");
                    }
                }
                
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating all employee report: " + e.getMessage());
        }
    }

     protected static void generateTotalPayByDivision(Scanner scanner) {
        try (Connection conn = getConnection()) {
            System.out.print("\nEnter the division for which you want to calculate the total pay: ");
            String division = scanner.nextLine();
    
            String sql = "SELECT SUM(salary) AS totalPay FROM employee WHERE division = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, division);
    
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        double totalPay = rs.getDouble("totalPay");
                        System.out.printf("The total pay for division '%s' is: $%.2f%n", division, totalPay);
                    } else {
                        System.out.println("No employees found for the specified division.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total pay by division: " + e.getMessage());
        }
    }
    
    protected static void generateTotalPayByJobTitle(Scanner scanner) {
        try (Connection conn = getConnection()) {
            System.out.print("\nEnter the job title for which you want to calculate the total pay: ");
            String jobTitle = scanner.nextLine();
    
            String sql = "SELECT SUM(salary) AS totalPay FROM employee WHERE jobTitle = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, jobTitle);
    
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        double totalPay = rs.getDouble("totalPay");
                        System.out.printf("The total pay for job title '%s' is: $%.2f%n", jobTitle, totalPay);
                    } else {
                        System.out.println("No employees found for the specified job title.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total pay by job title: " + e.getMessage());
        }
    }
    
}
