import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportManager {

    public void generateFullTimeEmployeeReport() {
        String query = """
            SELECT e.empID, e.first_name, e.last_name, e.jobTitle, ps.pay_date, ps.amount
            FROM Employee e
            LEFT JOIN PayStatements ps ON e.empID = ps.empID
            WHERE e.jobTitle = 'Full-Time';
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            System.out.println("Full-Time Employee Info with Pay Statement History:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s %s, Job Title: %s, Pay Date: %s, Amount: %.2f%n",
                    rs.getInt("empID"), rs.getString("first_name"), rs.getString("last_name"),
                    rs.getString("jobTitle"), rs.getDate("pay_date"), rs.getDouble("amount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateTotalPayByJobTitle(String month) {
        String query = """
            SELECT e.jobTitle, SUM(ps.amount) AS totalPay
            FROM Employee e
            JOIN PayStatements ps ON e.empID = ps.empID
            WHERE MONTH(ps.pay_date) = ?
            GROUP BY e.jobTitle;
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, month);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Total Pay by Job Title:");
                while (rs.next()) {
                    System.out.printf("Job Title: %s, Total Pay: %.2f%n",
                        rs.getString("jobTitle"), rs.getDouble("totalPay"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateTotalPayByDivision(String month) {
        String query = """
            SELECT e.division, SUM(ps.amount) AS totalPay
            FROM Employee e
            JOIN PayStatements ps ON e.empID = ps.empID
            WHERE MONTH(ps.pay_date) = ?
            GROUP BY e.division;
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, month);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Total Pay by Division:");
                while (rs.next()) {
                    System.out.printf("Division: %s, Total Pay: %.2f%n",
                        rs.getString("division"), rs.getDouble("totalPay"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
