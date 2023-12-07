package lv.tsi;

import lv.tsi.student.model.Student;
import lv.tsi.student.service.StudentService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;


public class StudentManager {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();

            Integer choice = readChoice(scanner);
            if (choice == null) continue;

            switch (choice) {
                case 1:
                    addStudent(service, scanner);
                    break;

                case 2:
                    service.printStudentList();
                    break;

                case 3:
                    searchStudent(service, scanner);
                    break;

                case 4:
                    deleteStudent(service, scanner);
                    break;

                case 5:
                    saveToFile(service, scanner);
                    break;

                case 6:
                    loadFromFile(service, scanner);
                    break;

                case 7:
                    endProgram(scanner);

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    static void addStudent(StudentService service, Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        LocalDate dob = null;
        boolean validDate = false;
        while (!validDate) {
            System.out.print("Enter student date of birth (YYYY-MM-DD): ");
            String dobString = scanner.nextLine();
            try {
                dob = LocalDate.parse(dobString);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please enter the date in the format YYYY-MM-DD.");
            }
        }

        Student student = new Student(name, email, dob);
        service.add(student);
    }

    static void searchStudent(StudentService service, Scanner scanner) {
        System.out.print("Enter student name to search: ");
        scanner.nextLine(); // Consume newline character
        String searchName = scanner.nextLine();
        List<Student> students = service.searchStudentByName(searchName);
        students.forEach(s -> System.out.println(s.toString()));
    }

    static void deleteStudent(StudentService service, Scanner scanner) {
        System.out.print("Enter student id to delete: ");
        scanner.nextLine(); // Consume newline character
        String saveFileName = scanner.nextLine();
        service.saveToFile(saveFileName);
    }

    static void saveToFile(StudentService service, Scanner scanner) {
        System.out.print("Enter file name to save: ");
        scanner.nextLine(); // Consume newline character
        String saveFileName = scanner.nextLine();
        service.saveToFile(saveFileName);
    }

    static void loadFromFile(StudentService service, Scanner scanner) {
        System.out.print("Enter file name to load: ");
        String loadFileName = scanner.nextLine();
        scanner.nextLine(); // Consume newline character
        service.loadFromFile(loadFileName);
    }

    private static Integer readChoice(Scanner scanner) {
        String choiceInput = scanner.nextLine();

        if (!isNumeric(choiceInput)) {
            System.out.println("Invalid input. Please enter a numeric choice.");
            return null;
        }

        return Integer.parseInt(choiceInput);
    }

    private static void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Add Student");
        System.out.println("2. List Students");
        System.out.println("3. Search Student by Name");
        System.out.println("4. Delete student by it");
        System.out.println("5. Save Student List to File");
        System.out.println("6. Load Student List from File");
        System.out.println("7. Exit");
    }

    private static void endProgram(Scanner scanner) {
        System.out.println("Exiting program. Goodbye!");
        scanner.close();
        System.exit(0);
    }

    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}