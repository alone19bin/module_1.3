package org.maxim.crud.view;



import org.maxim.crud.controller.DeveloperController;
import org.maxim.crud.controller.SkillController;
import org.maxim.crud.enums.DeveloperStatus;
import org.maxim.crud.enums.SkillStatus;
import org.maxim.crud.enums.SpecialtyStatus;
import org.maxim.crud.model.Developer;
import org.maxim.crud.model.Skill;
import org.maxim.crud.model.Specialty;
import org.maxim.crud.repository.DeveloperRepository;
import org.maxim.crud.repository.gson.GsonDeveloperRepositoryImpl;
import org.maxim.crud.repository.gson.GsonSkillRepositoryImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private final DeveloperController developerController;
    private final SkillController skillController;
    private final Scanner scanner;

    public DeveloperView(DeveloperController developerController, SkillController skillController) {
        this.developerController = developerController;
        this.skillController = skillController;
        this.scanner = new Scanner(System.in);
    }


    public void createDeveloper(){
        System.out.println("Введи firstname: ");
        String firstname = scanner.nextLine();


        System.out.println("Введи lastname: ");
        String lastname = scanner.nextLine();


        List<Skill> skills = new ArrayList<>();


        System.out.println("Введи specialty: ");
        String specialty = scanner.nextLine();


        DeveloperStatus developerStatus = DeveloperStatus.ACTIVE;

        Developer newDeveloper = developerController.createDeveloper(firstname, lastname, skills, specialty, developerStatus);
        System.out.println("Developer создан с ID: " + newDeveloper.getId());
    }

    public void getDeveloperByID(){
        System.out.println("Введи id developera");
        long id = scanner.nextLong();
        Developer developer = developerController.getDeveloperById(id);
        System.out.println("Developer найден " + developer);
    }
    public void getAllDeveloper(){
        List<Developer> developers = developerController.getAllDeveloper();
          for(Developer developer : developers){
            System.out.println(developer);
        }
    }
    public void deleteDeveloper(){
        System.out.print("Ввведи id для удаления developra ");
        Long id = scanner.nextLong();
        developerController.deleteDeveloper(id);
        System.out.println("Developer удален");
    }

    public void updateDeveloper(){
        System.out.println("Введи id developra для обновления");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("ВВеди firstname");
        String firstname = scanner.nextLine();

        System.out.println("ВВеди lastname: ");
        String lastname = scanner.nextLine();

        List<Skill> skills = new ArrayList<>();

        System.out.println("Enter specialty: ");
        String specialty = scanner.nextLine();



        DeveloperStatus developerStatus = DeveloperStatus.ACTIVE;

        Developer updateDeveloper = developerController.updateDeveloper(id, firstname, lastname, skills, specialty, developerStatus);
        System.out.println("Id обновленного developer: " + updateDeveloper.getId());
    }


    public void addSkillToDeveloper() {
        System.out.print("Введи Developer ID для добавления Skill: ");
        Long developerId = scanner.nextLong();

        List<Skill> skills = skillController.getAllSkill();
        List<Skill> selectedSkills = new ArrayList<>();
        System.out.println(skills);
        System.out.print("Введи Skill ID который нужно добавить: ");
        Long id = scanner.nextLong();

            while (id != -1) {
                Long finalId = id;
                Skill skillToAdd = skills.stream().filter(s -> s.getId().equals(finalId)).findFirst().orElse(null);

                    if (skillToAdd != null) {
                        selectedSkills.add(skillToAdd);
                    } else {
                        System.out.println("Недопустимое значение, введи значение заново");
                        break;
                    }

                System.out.println("Введи Id для добавления еще одного Skill, либо введи 0 для выхода");
                id = scanner.nextLong();
            }
            Developer updateDeveloper = developerController.addSkillToDeveloper(developerId, selectedSkills);
            System.out.println("Skill добавлен в ID: " + updateDeveloper.getId());
    }


    public void start() {
        boolean start = true;

        while (start) {
            System.out.println("1.Создать пользователя");
            System.out.println("2.Найти Developra по Id");
            System.out.println("3.Найти всех Developr'ов");
            System.out.println("4.Удалить пользователя");
            System.out.println("5.Добавить Skill");
            System.out.println("6.Обновить пользователя");
            System.out.println("7.Выход");

            int number = scanner.nextInt();
            scanner.nextLine();

            switch (number) {
                case 1:
                    createDeveloper();
                    break;
                case 2:
                    getDeveloperByID();
                    break;
                case 3:
                    getAllDeveloper();
                    break;
                case 4:
                    deleteDeveloper();
                    break;
                case 5:
                    addSkillToDeveloper();
                    break;
                case 6:
                    updateDeveloper();
                    break;
                case 7:
                    start = false;
                    break;
                default:
                    System.out.println("Некорректоое действие, введите значение заново");
                    break;
            }
        }
    }
}
