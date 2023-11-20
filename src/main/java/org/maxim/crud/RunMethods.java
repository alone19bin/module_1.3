package org.maxim.crud;

import org.maxim.crud.controller.DeveloperController;
import org.maxim.crud.controller.SkillController;
import org.maxim.crud.controller.SpecialtyController;
import org.maxim.crud.repository.DeveloperRepository;
import org.maxim.crud.repository.SkillRepository;
import org.maxim.crud.repository.SpecialtyRepository;
import org.maxim.crud.repository.gson.GsonDeveloperRepositoryImpl;
import org.maxim.crud.repository.gson.GsonSkillRepositoryImpl;
import org.maxim.crud.repository.gson.GsonSpecialtyRepositoryImpl;
import org.maxim.crud.view.DeveloperView;
import org.maxim.crud.view.SkillView;
import org.maxim.crud.view.SpecialtyView;

public class RunMethods {
    public void developerRun() {
        DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();
        DeveloperController developerController = new DeveloperController(developerRepository);
        SkillController skillController = new SkillController(new GsonSkillRepositoryImpl());
        DeveloperView DeveloperView = new DeveloperView(developerController, skillController);
        DeveloperView.start();
    }

    public void skillRun() {
        SkillRepository skillRepository = new GsonSkillRepositoryImpl();
        SkillController skillController = new SkillController(skillRepository);
        SkillView skillView = new SkillView(skillController);
        skillView.start();
    }
    public  void SpecialtyRun() {
        SpecialtyRepository specialtyRepository = new GsonSpecialtyRepositoryImpl();
        SpecialtyController specialtyController = new SpecialtyController(specialtyRepository);
        SpecialtyView specialtyView = new SpecialtyView(specialtyController);
        specialtyView.start();
    }
}
