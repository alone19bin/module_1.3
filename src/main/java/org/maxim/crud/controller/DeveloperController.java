package org.maxim.crud.controller;

import lombok.RequiredArgsConstructor;
import org.maxim.crud.enums.DeveloperStatus;
import org.maxim.crud.model.Developer;
import org.maxim.crud.model.Skill;

import org.maxim.crud.model.Specialty;
import org.maxim.crud.repository.DeveloperRepository;

import java.util.List;

@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperRepository developerRepository;

    public Developer createDeveloper(String firstname, String lastname,
                                     List<Skill>skills, String specialty, DeveloperStatus developerStatus) {

        Developer newDeveloper = Developer.builder()
                .firstName(firstname)
                .lastName(lastname)
                .skills(skills)
                .specialty(specialty)
                .developerStatus(developerStatus)
                .build();

        return developerRepository.save(newDeveloper);
    }

    public Developer getDeveloperById(Long id){
        return developerRepository.getById(id);
    }
    public List<Developer> getAllDeveloper(){
        return developerRepository.getAll();
    }
    public void deleteDeveloper(Long id){
        developerRepository.deleteById(id);
    }

    public Developer updateDeveloper(Long id, String firstname, String lastname,
                                  List<Skill>skills, String specialty, DeveloperStatus developerStatus){

        Developer updateDeveloper = Developer.builder()
                .firstName(firstname)
                .lastName(lastname)
                .skills(skills)
                .specialty(specialty)
                .developerStatus(developerStatus)
                .build();
        updateDeveloper.setId(id);

        return  developerRepository.update(updateDeveloper);
    }
    public Developer addSkillToDeveloper(Long developerId ,List<Skill> skillsToAdd) {
        Developer developer = developerRepository.getById(developerId);
        developer.getSkills().addAll(skillsToAdd);
        return developerRepository.update(developer);
    }
}



