package com.projectmaximize;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeApp {
    private Scanner scanner;
    private EmployeeImpl employee = null;

    public EmployeeApp() {
        this.scanner = new Scanner(System.in);
        this.employee = new EmployeeImpl("Default Employee");
    }

    public void run() {

        ArrayList<EmployeeImpl> Employees = new ArrayList<>();
        Employees.add(new EmployeeImpl("Max"));
        Employees.add(new EmployeeImpl("Joseph"));
        Employees.add(new EmployeeImpl("Nabaa"));
        Employees.add(new EmployeeImpl("Ahmad"));

        System.out.println("\n--- Enter username ---");
        String name = scanner.nextLine();

        employee = findEmployeeByName(Employees, name);

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
        scanner.close();
    }

    private void logHours() {
        System.out.print("You have the following activities: ");
        employee.printAllActivities();
        System.out.print("Enter activity ID: ");
        String activityId = scanner.nextLine();
        System.out.print("Enter hours: ");
        int hours = Integer.parseInt(scanner.nextLine());
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
