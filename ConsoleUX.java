import java.util.Scanner;

public class ConsoleUX {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Generate Reports");
            System.out.println("5. Update Employee Data");
            System.out.println("6. Update Salaries by Percentage Range");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> employeeManager.addEmployee(scanner);
                case 2 -> employeeManager.searchEmployee(scanner);
                case 3 -> employeeManager.deleteEmployee(scanner);
                case 4 -> generateReports(scanner);
                case 5 -> employeeManager.updateEmployeeData(scanner);
                case 6 -> employeeManager.updateSalariesByPercentage(scanner);
                case 7 -> {
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    

    private static void generateReports(Scanner scanner) {
        System.out.println("\n--- Generate Reports ---");
        System.out.println("1. All employees with pay statements");
        System.out.println("2. Total pay for month by job title");
        System.out.println("3. Total pay for month by division");
        System.out.print("Enter your choice: ");
    
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline
    
        switch (choice) {
            case 1 -> reportGenerator.generateAllEmployeeReport();
            case 2 -> reportGenerator.generateTotalPayByJobTitle(scanner);
            case 3 -> reportGenerator.generateTotalPayByDivision(scanner);
            default -> System.out.println("Invalid choice. Returning to main menu.");
        }
    }   
 
}
