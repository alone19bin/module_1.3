package org.maxim.crud.view;

import org.maxim.crud.controller.SkillController;
import org.maxim.crud.enums.SkillStatus;
import org.maxim.crud.model.Skill;
import org.maxim.crud.repository.SkillRepository;
import org.maxim.crud.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class SkillView {

    private final SkillController skillController;
    private final Scanner scanner;

    public SkillView(SkillController skillController) {
        this.skillController = skillController;
        this.scanner = new Scanner(System.in);
    }

    public void createSkill(){
        System.out.println("Введи: ");
        String name = scanner.nextLine();

        SkillStatus skillStatus = SkillStatus.ACTIVE;

        Skill newSkill = skillController.createSkill(name, skillStatus);
        System.out.println("Skill создан в ID: " + newSkill.getId());
    }

    public void getSkillById(){
        System.out.println("Введи Skill id: ");
        long id = scanner.nextLong();
        Skill skill = skillController.getSkillById(id);
        System.out.println("Скилл надйен: " + skill);
    }

    public void getAllSkills(){
        List<Skill> skills = skillController.getAllSkill();
        for(Skill skill : skills){
            System.out.println(skill);
        }
    }

    public void updateSkill(){
        System.out.println("Введи Skill: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Введи название: ");
        String name = scanner.nextLine();

        SkillStatus skillStatus = SkillStatus.ACTIVE;

        Skill updateSkill = skillController.updateSkill(id, name, skillStatus);
        System.out.println("Skill обновлен в Id: " + updateSkill.getId());
    }

    public void deleteSkill(){
        System.out.print("Введи id Skill: ");
        Long id = scanner.nextLong();
        skillController.deleteSkill(id);
        System.out.println("Skill удален.");
    }

    public void start(){
        boolean start = true;

        while (start){
            System.out.println("1. Создать Skill");
            System.out.println("2. Получить ID Skill");
            System.out.println("3. Получить всех Skills");
            System.out.println("4. Обновить Skill");
            System.out.println("5. Удалить Skill");
            System.out.println("6. Выход");
            System.out.print("Выбор: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1 :
                    createSkill();
                    break;
                case 2:
                    getSkillById();
                    break;
                case 3:
                    getAllSkills();
                    break;
                case 4:
                    updateSkill();
                    break;
                case 5:
                    deleteSkill();
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
