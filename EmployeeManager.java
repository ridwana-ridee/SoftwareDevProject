import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeManager 
{

    public void searchEmployee(String name, String ssn, Integer empID) 
    {
        String query = """
            SELECT * FROM Employee
            WHERE first_name LIKE ? OR ssn = ? OR empID = ?;
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) 
        {
            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, ssn);
            stmt.setObject(3, empID);

            try (ResultSet rs = stmt.executeQuery()) 
            {
                while (rs.next()) 
                {
                    System.out.printf("ID: %d, Name: %s %s, SSN: %s, Job Title: %s, Division: %s, Salary: %.2f%n",
                        rs.getInt("empID"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("ssn"), rs.getString("jobTitle"), rs.getString("division"),
                        rs.getDouble("salary"));
                }
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void createEmployee(String firstName, String lastName, String ssn, String jobTitle, String division, double salary) 
    {
        String query = """
            INSERT INTO Employee (first_name, last_name, ssn, jobTitle, division, salary)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) 
        {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, ssn);
            stmt.setString(4, jobTitle);
            stmt.setString(5, division);
            stmt.setDouble(6, salary);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) 
            {
                System.out.println("Employee created successfully.");
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    
    public void updateEmployeeData(int empID, String fieldName, String newValue) 
    {
        String query = String.format("UPDATE Employee SET %s = ? WHERE empID = ?", fieldName);

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) 
        {
            stmt.setString(1, newValue);
            stmt.setInt(2, empID);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void updateSalaries(double percentageIncrease, double minSalary, double maxSalary) 
    {
        String query = """
            UPDATE Employee
            SET salary = salary + (salary * ? / 100)
            WHERE salary BETWEEN ? AND ?;
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) 
        {
            stmt.setDouble(1, percentageIncrease);
            stmt.setDouble(2, minSalary);
            stmt.setDouble(3, maxSalary);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Salaries updated for rows: " + rowsUpdated);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteEmployee(int empID) 
    {
        String query = "DELETE FROM Employee WHERE empID = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) 
        {
            stmt.setInt(1, empID);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) 
            {
                System.out.println("Employee deleted successfully.");
            } else 
            {
                System.out.println("No employee found with the provided empID.");
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

}
