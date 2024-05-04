package com.projectmaximize;

import java.util.Scanner;
import java.util.UUID;

public class App {

    private static ProjectManagerImpl projectManager = new ProjectManagerImpl("Default Manager");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Create Project");
            System.out.println("2. Add Activity to Project");
            System.out.println("3. Allocate Resource");
            System.out.println("4. Show Project Report");
            System.out.println("5. Exit");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    createProject();
                    break;
                case "2":
                    addActivityToProject();
                    break;
                case "3":
                    allocateResource();
                    break;
                case "4":
                    showProjectReport();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please choose again.");
            }
        }
        scanner.close();
    }

    private static void createProject() {
        System.out.println("Enter project name:");
        String name = scanner.nextLine();
        System.out.println("Enter project description:");
        String description = scanner.nextLine();
        projectManager.createProject(name, description);
        System.out.println("Project created successfully.");
    }

    private static void addActivityToProject() {
        System.out.println("Enter the project ID:");
        String projectId = scanner.nextLine();
        System.out.println("Enter activity name:");
        String activityName = scanner.nextLine();
        System.out.println("Enter estimated hours:");
        int hours = Integer.parseInt(scanner.nextLine());
        projectManager.createActivity(projectId, activityName, hours);
        System.out.println("Activity added successfully.");
    }

    private static void allocateResource() {
        System.out.println("Enter the activity ID:");
        String activityId = scanner.nextLine();
        System.out.println("Enter employee name:");
        String name = scanner.nextLine();
        Employee employee = new EmployeeImpl(name);
        projectManager.allocateResource(activityId, employee);
        System.out.println("Resource allocated successfully.");
    }

    private static void showProjectReport() {
        System.out.println("Enter the project ID for which to generate the report:");
        String projectId = scanner.nextLine();
        ProjectReportGeneratorImpl reportGenerator = new ProjectReportGeneratorImpl(projectManager);
        System.out.println(reportGenerator.generateTimeReport(UUID.fromString(projectId)));
    }
}
