package org.maxim.crud.controller;

import lombok.RequiredArgsConstructor;
import org.maxim.crud.enums.SkillStatus;
import org.maxim.crud.model.Skill;
import org.maxim.crud.repository.SkillRepository;
import org.maxim.crud.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;
@RequiredArgsConstructor
public class SkillController {

    private final SkillRepository skillRepository;
    public Skill createSkill(String name, SkillStatus skillStatus){
        Skill newSkill = Skill.builder()
                .name(name)
                .skillStatus(skillStatus)
                .build();

        return skillRepository.save(newSkill);
    }

    public Skill getSkillById(Long id){
        return skillRepository.getById(id);
    }

    public List<Skill> getAllSkill(){
        return skillRepository.getAll();
    }

    public Skill updateSkill(Long id, String name, SkillStatus skillStatus){
        Skill updateSkill = Skill.builder()
                .name(name)
                .skillStatus(skillStatus)
                .build();

        updateSkill.setId(id);

        return skillRepository.update(updateSkill);
    }

    public void deleteSkill(Long id){
        skillRepository.deleteById(id);
    }
}

