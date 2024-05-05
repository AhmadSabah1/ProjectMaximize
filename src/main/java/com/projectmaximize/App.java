package com.projectmaximize;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<EmployeeImpl> employees = new ArrayList<>();
        employees.add(new EmployeeImpl("Max"));
        employees.add(new EmployeeImpl("Joseph"));
        employees.add(new EmployeeImpl("Nabaa"));
        employees.add(new EmployeeImpl("Ahmad"));

        ArrayList<ProjectManagerImpl> managers = new ArrayList<>();
        managers.add(new ProjectManagerImpl("Kasper"));
        managers.add(new ProjectManagerImpl("Hans"));
        managers.add(new ProjectManagerImpl("Christian"));
        managers.add(new ProjectManagerImpl("Hussein"));



        int appType = 0;

        while(appType != 3) {
            System.out.println("1. Admin app");
            System.out.println("2. Employee app");
            System.out.println("3. Exit");
            appType = Integer.parseInt(scanner.nextLine());
            
            if (appType == 1) {
                ProjectManagerApp projectManagerApp = new ProjectManagerApp(employees, managers);
                projectManagerApp.run();
            } else if (appType == 2) {
                EmployeeApp employeeApp = new EmployeeApp(employees);
                employeeApp.run();
            } 
            else {
                System.out.println("Invalid choise. Try again");
                appType = Integer.parseInt(scanner.nextLine());
            }
        }

        scanner.close();
    }
}
