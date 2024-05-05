package com.projectmaximize;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeApp {
    private Scanner scanner;
    private EmployeeImpl employee = null;
    private ArrayList<EmployeeImpl> employees;

    public EmployeeApp(ArrayList<EmployeeImpl> employees) {
        this.scanner = new Scanner(System.in);
        this.employee = new EmployeeImpl("Default Employee");
        this.employees = employees;
    }

    public void run() {

        System.out.println("\n--- Enter username ---");
        String name = scanner.nextLine();

        employee = findEmployeeByName(employees, name);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. Log Hours");
            System.out.println("2. View My Tasks");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    logHours();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void logHours() {
        System.out.print("You have the following activities: ");
        employee.printAllActivities();
        System.out.print("Enter activity ID: ");
        String activityId = scanner.nextLine();
        System.out.print("Enter hours: ");
        int hours = Integer.parseInt(scanner.nextLine());
        employee.logHours(activityId, hours);
        System.out.println("Hours logged successfully.");
    }

    private void viewTasks() {
        System.out.println("Displaying tasks for: " + employee.getName());
    }

    private static EmployeeImpl findEmployeeByName(ArrayList<EmployeeImpl> employees, String name) {
        boolean found = false;

        for (EmployeeImpl employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                System.out.println("Employee found: " + employee.getName());
                found = true;
                return employee;
            }
        }

        if (!found) {
            System.out.println("No employee found with the name: " + name);
        }

        return null;
    }

}
