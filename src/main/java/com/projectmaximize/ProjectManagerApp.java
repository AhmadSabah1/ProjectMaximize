package com.projectmaximize;

import java.util.Scanner;
import java.util.UUID;

public class ProjectManagerApp {
    private ProjectManagerImpl projectManager;
    private Scanner scanner;

    public ProjectManagerApp() {
        this.projectManager = new ProjectManagerImpl("Manager Name");
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Project Manager Menu ---");
            System.out.println("1. Create Project");
            System.out.println("2. Add Activity to Project");
            System.out.println("3. Allocate Resource");
            System.out.println("4. Generate Project Report");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    createProject();
                    break;
                case 2:
                    addActivityToProject();
                    break;
                case 3:
                    allocateResource();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
        scanner.close();
    }

    private void createProject() {
        System.out.print("Enter project name: ");
        String name = scanner.nextLine();
        System.out.print("Enter project description: ");
        String description = scanner.nextLine();
        projectManager.createProject(name, description);
        System.out.println("Project created successfully.");
    }

    private void addActivityToProject() {
        System.out.print("Enter project ID: ");
        String projectId = scanner.nextLine();
        System.out.print("Enter activity name: ");
        String activityName = scanner.nextLine();
        System.out.print("Enter estimated hours: ");
        int hours = Integer.parseInt(scanner.nextLine());
        projectManager.createActivity(projectId, activityName, hours);
        System.out.println("Activity added successfully.");
    }

    private void allocateResource() {
        System.out.print("Enter activity ID: ");
        String activityId = scanner.nextLine();
        System.out.print("Enter employee name: ");
        String employeeName = scanner.nextLine();
        EmployeeImpl employee = new EmployeeImpl(employeeName);
        projectManager.allocateResource(activityId, employee);
        System.out.println("Resource allocated successfully.");
    }

    private void generateReport() {
        System.out.print("Enter project ID: ");
        String projectId = scanner.nextLine();
        ProjectReportGeneratorImpl reportGenerator = new ProjectReportGeneratorImpl(projectManager);
        System.out.println(reportGenerator.generateTimeReport(UUID.fromString(projectId)));
    }

    public static void main(String[] args) {
        new ProjectManagerApp().run();
    }
}
