package org.maxim.crud;



import java.util.Scanner;

public class StartProgram {
    Scanner scanner = new Scanner(System.in);
    RunMethods runMethods = new RunMethods();

    public void start() {
        System.out.println("Выберите действие:");
        System.out.println("1. Developer");
        System.out.println("2. Skill");
        System.out.println("3. Specialty");


        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                developerRun();
            case 2:
                skillRun();
            case 3:
                SpecialtyRun();
                break;
            default:
                System.out.println("Некорректное действие");
        }
    }

    public void developerRun() {
        runMethods.developerRun();
    }

    public void skillRun() {
        runMethods.skillRun();
    }
    public  void SpecialtyRun() {
        runMethods.SpecialtyRun();
    }
}
