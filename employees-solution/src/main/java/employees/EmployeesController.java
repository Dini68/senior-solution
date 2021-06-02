package employees;

import java.util.Scanner;

public class EmployeesController {

    private Scanner scanner = new Scanner(System.in);

//    private EmployeesService employeesService =
//            new EmployeesService(new InMemoryEmployeesRepository());

    private EmployeesService employeesService =
            new EmployeesService(new MariadbEmployeesRepository());

    public static void main(String[] args) {
        new EmployeesController().start();
    }

    private void start() {
        System.out.println("Menu");

        for (int i = 0; i < 5; i++) {
            String name = scanner.nextLine();
            employeesService.save(name);
        }

        System.out.println(employeesService.listEmployees());
    }
}
