package org.maxim.crud.view;




import org.maxim.crud.controller.SpecialtyController;
import org.maxim.crud.enums.SpecialtyStatus;
import org.maxim.crud.model.Specialty;

import java.util.List;
import java.util.Scanner;

public class SpecialtyView {
    private final SpecialtyController specialtyController;
    private final Scanner scanner;

    public  SpecialtyView( SpecialtyController  specialtyController) {
        this. specialtyController =  specialtyController;
        this.scanner = new Scanner(System.in);
    }

    public void createSpecialty(){
        System.out.println("Введи: ");
        String name = scanner.nextLine();

        SpecialtyStatus specialtyStatus = SpecialtyStatus.ACTIVE;

        Specialty newSpecialty = specialtyController.createSpecialty(name, specialtyStatus);
        System.out.println("Skill создан в ID: " + newSpecialty.getId());
    }

    public void getSpecialtyById(){
        System.out.println("Введи Specialty id: ");
        long id = scanner.nextLong();
        Specialty skill = specialtyController.getSpecialtyById(id);
        System.out.println("Скилл надйен: " + skill);
    }

    public void getAllSpecialtys(){
        List<Specialty> specialtys = specialtyController.getAllSpecialty();
        for(Specialty specialty : specialtys){
            System.out.println(specialty);
        }
    }

    public void updateSpecialty(){
        System.out.println("Введи Specialty: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Введи название: ");
        String name = scanner.nextLine();

        SpecialtyStatus specialtyStatus = SpecialtyStatus.ACTIVE;

        Specialty updateSpecialty = specialtyController.updateSpecialty(id, name, specialtyStatus);
        System.out.println("Specialty обновлен в Id: " + updateSpecialty.getId());
    }

    public void deleteSpecialty(){
        System.out.print("Введи id Specialty: ");
        Long id = scanner.nextLong();
        specialtyController.deleteSpecialty(id);
        System.out.println("Specialty удален.");
    }

    public void start(){
        boolean start = true;

        while (start){
            System.out.println("1. Создать Specialty");
            System.out.println("2. Получить ID Specialty");
            System.out.println("3. Получить всех Specialty");
            System.out.println("4. Обновить Specialty");
            System.out.println("5. Удалить Specialty");
            System.out.println("6. Выход");
            System.out.print("Выбор: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1 :
                    createSpecialty();
                    break;
                case 2:
                    getSpecialtyById();
                    break;
                case 3:
                    getAllSpecialtys();
                    break;
                case 4:
                    updateSpecialty();
                    break;
                case 5:
                    deleteSpecialty();
                    break;
                case 6:
                    start = false;
                    break;
                default:
                    System.out.println("Некорректоое действие, введите значение заново");
                    break;
            }
        }
    }


}

