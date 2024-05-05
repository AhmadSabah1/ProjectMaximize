package com.projectmaximize;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("1. Admin app");
        System.out.println("2. Employee app");

        int appType = Integer.parseInt(scanner.nextLine());

        if (appType == 1) {
            ProjectManagerApp projectManagerApp = new ProjectManagerApp();
            projectManagerApp.run();
        } else if (appType == 2) {
            EmployeeApp employeeApp = new EmployeeApp();
            employeeApp.run();
        } else {
            System.out.println("Invalid choise. Try again");
            appType = Integer.parseInt(scanner.nextLine());
        }

    }
}
