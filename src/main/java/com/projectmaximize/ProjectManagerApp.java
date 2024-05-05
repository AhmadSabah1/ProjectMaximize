package com.projectmaximize;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.projectmaximize.interfaces.Activity;

public class ProjectManagerApp {
    private ProjectManagerImpl projectManager;
    private Scanner scanner;
    private ArrayList<EmployeeImpl> employees;
    ArrayList<ProjectManagerImpl> projectManagers;

    public ProjectManagerApp(ArrayList<EmployeeImpl> employees, ArrayList<ProjectManagerImpl> projectManagers) {
        this.scanner = new Scanner(System.in);
        this.employees = employees;
        this.projectManagers = projectManagers;
    }

    public void run() {
        System.out.println("\n--- Enter Project Manager Name ---");
        String name = scanner.nextLine();
        projectManager = findManagerByName(name);
        if (projectManager == null) {
            System.out.println("Project Manager not found.");
            return;
        }

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
                    addActivityToProjectInteractive();
                    break;
                case 3:
                    allocateResourceInteractive();
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
    }

    private void createProject() {
        System.out.print("Enter project name: ");
        String name = scanner.nextLine();
        System.out.print("Enter project description: ");
        String description = scanner.nextLine();
        projectManager.createProject(name, description);
        System.out.println("Project created successfully.");
    }

    private void addActivityToProject(ProjectImpl project) {
        System.out.print("Enter activity name: ");
        String activityName = scanner.nextLine();
        System.out.print("Enter estimated hours: ");
        int hours = Integer.parseInt(scanner.nextLine());
        project.addActivity(new ActivityImpl(activityName, hours));
        System.out.println("Activity added successfully.");
    }

    private void addActivityToProjectInteractive() {
        ProjectImpl selectedProject = selectProject();
        if (selectedProject == null)
            return;
        addActivityToProject(selectedProject);
    }

    private void allocateResource() {
        System.out.print("Enter activity ID: ");
        String activityId = scanner.nextLine();
        System.out.print("Enter employee name: ");
        String employeeName = scanner.nextLine();

        // Find the employee in the employee list based on the name
        EmployeeImpl employee = findEmployeeByName(employeeName);
        if (employee != null) {
            projectManager.allocateResource(activityId, employee);

            System.out.println("Resource allocated successfully.");
        } else {
            System.out.println("Employee not found: " + employeeName);
        }
    }

    private void generateReport() {
        ProjectImpl selectedProject = selectProject();
        if (selectedProject == null) {
            System.out.println("No project selected or invalid selection.");
            return;
        }
    
        // Assuming that ProjectImpl class has a method to get the UUID or similar identifier
        String projectId = selectedProject.getProjectId();  // Method getProjectId() should return a UUID
        ProjectReportGeneratorImpl reportGenerator = new ProjectReportGeneratorImpl(projectManager);
        System.out.println(reportGenerator.generateTimeReport(projectId));
    }

    private EmployeeImpl findEmployeeByName(String name) {
        for (EmployeeImpl employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                return employee;
            }
        }
        return null;
    }

    private void allocateResourceInteractive() {
        ProjectImpl selectedProject = selectProject();
        if (selectedProject == null)
            return;

        Activity selectedActivity = selectActivity(selectedProject);
        if (selectedActivity == null)
            return;

        EmployeeImpl selectedEmployee = selectEmployee();
        if (selectedEmployee == null)
            return;

        projectManager.allocateResource(selectedActivity.getId(), selectedEmployee);
        System.out.println("Resource allocated successfully to " + selectedActivity.getName());
    }

    private ProjectImpl selectProject() {
        List<ProjectImpl> projects = new ArrayList<>(projectManager.getProjects().values());
        if (projects.isEmpty()) {
            System.out.println("No projects available.");
            return null;
        }

        System.out.println("Select a project:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i).getName());
        }

        System.out.print("Enter the number of the project: ");
        int projectIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (projectIndex >= 0 && projectIndex < projects.size()) {
            return projects.get(projectIndex);
        }
        System.out.println("Invalid project selection.");
        return null;
    }

    private Activity selectActivity(ProjectImpl project) {
        List<Activity> activities = project.getActivities();
        if (activities.isEmpty()) {
            System.out.println("No activities available in this project.");
            return null;
        }

        System.out.println("Select an activity:");
        for (int i = 0; i < activities.size(); i++) {
            System.out.println((i + 1) + ". " + activities.get(i).getName());
        }

        System.out.print("Enter the number of the activity: ");
        int activityIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (activityIndex >= 0 && activityIndex < activities.size()) {
            return activities.get(activityIndex);
        }
        System.out.println("Invalid activity selection.");
        return null;
    }

    private EmployeeImpl selectEmployee() {
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
            return null;
        }

        System.out.println("Select an employee:");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". " + employees.get(i).getName());
        }

        System.out.print("Enter the number of the employee: ");
        int employeeIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (employeeIndex >= 0 && employeeIndex < employees.size()) {
            return employees.get(employeeIndex);
        }
        System.out.println("Invalid employee selection.");
        return null;
    }

    private ProjectManagerImpl findManagerByName(String name) {
        return projectManagers.stream()
                .filter(manager -> manager.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}
