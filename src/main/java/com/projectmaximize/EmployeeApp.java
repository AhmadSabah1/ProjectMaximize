package com.projectmaximize;

import java.util.Scanner;

public class EmployeeApp {
    private Scanner scanner;
    private EmployeeImpl employee;  // Assume this could be fetched or logged in differently

    public EmployeeApp() {
        this.scanner = new Scanner(System.in);
        this.employee = new EmployeeImpl("Default Employee");  // Placeholder for actual authentication/log-in
    }

    public void run() {
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
        System.out.print("Enter activity ID: ");
        String activityId = scanner.nextLine();
        System.out.print("Enter hours: ");
        int hours = Integer.parseInt(scanner.nextLine());
        // Logic to log hours should be implemented
        System.out.println("Hours logged successfully.");
    }

    private void viewTasks() {
        // Logic to view tasks should be implemented
        System.out.println("Displaying tasks for: " + employee.getName());
    }

    public static void main(String[] args) {
        new EmployeeApp().run();
    }
}

