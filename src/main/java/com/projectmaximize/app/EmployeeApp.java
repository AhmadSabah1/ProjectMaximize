package com.projectmaximize.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.projectmaximize.core.EmployeeImpl;
import com.projectmaximize.interfaces.Activity;

//Max
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
                    logHoursInteractive();
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

    private void logHoursInteractive() {
        Activity selectedActivity = selectActivityFromEmployee();
        if (selectedActivity == null) {
            System.out.println("No valid activity selected or available.");
            return;
        }

        System.out.print("Enter the number of hours to log for " + selectedActivity.getName() + ": ");
        int hours;
        try {
            hours = Integer.parseInt(scanner.nextLine());
            if (hours > 0) {
                employee.logHours(selectedActivity.getId(), hours);
                System.out.println("Hours successfully logged.");
            } else {
                System.out.println("Please enter a positive number of hours.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric value.");
        }
    }

    private Activity selectActivityFromEmployee() {
        List<Activity> activities = employee.getActivities();
        if (activities.isEmpty()) {
            System.out.println("You have no activities assigned.");
            return null;
        }

        System.out.println("Select an activity to log hours:");
        for (int i = 0; i < activities.size(); i++) {
            System.out.println((i + 1) + ". " + activities.get(i).getName());
        }

        System.out.print("Enter the number of the activity: ");
        int activityIndex;
        try {
            activityIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (activityIndex >= 0 && activityIndex < activities.size()) {
                return activities.get(activityIndex);
            } else {
                System.out.println("Invalid activity selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric value.");
        }
        return null;
    }


    private void viewTasks() {
        System.out.println("Displaying tasks for: " + employee.getName());
        employee.printAllActivities();
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
