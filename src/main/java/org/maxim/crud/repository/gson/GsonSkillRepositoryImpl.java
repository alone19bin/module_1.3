package org.maxim.crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.maxim.crud.enums.SkillStatus;
import org.maxim.crud.model.Developer;
import org.maxim.crud.model.Skill;
import org.maxim.crud.repository.SkillRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GsonSkillRepositoryImpl implements SkillRepository {
    private final Gson gson = new Gson();
    private final String FILE_PATH = "src/main/resources/skills.json";

    private List<Skill> loadSkills(){
        try(Reader skillreader = new FileReader(FILE_PATH)){
            Type type = new TypeToken<List<Skill>>(){}.getType();
            List<Skill> skills = gson.fromJson(skillreader, type);

            if(skills == null){
                skills = new ArrayList<>();
            }

            return skills;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private void saveSkills(List<Skill> skills){
        try(FileWriter skillswriter = new FileWriter(FILE_PATH)){
            gson.toJson(skills, skillswriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill getById(Long id) {
        return loadSkills()
                .stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return loadSkills();
    }

    @Override
    public Skill save(Skill skill) {
        List<Skill> skills = loadSkills();

        skill.setId(IdGenerator.generatedId(skills, Skill::getId));

        skills.add(skill);
        saveSkills(skills);

        return skill;
    }



    @Override
    public Skill update(Skill updateSkill) {
        List<Skill> skills = loadSkills();
        List<Skill> updateSkills = skills
                .stream()
                .map(d -> {
                    if (d.getId().equals(updateSkill.getId())){
                        return updateSkill;
                    }
                    return d;
                }).toList();

        saveSkills(updateSkills);

        return updateSkill;
    }

    @Override
    public void deleteById(Long id) {
        List<Skill> skills = loadSkills();

        List<Skill> deleteSkills = skills
                .stream()
                .map(skill -> {
                    if(skill.getId().equals(id)){
                        skill.setSkillStatus(SkillStatus.DELETED);
                    }
                    return skill;
                }).toList();

        saveSkills(deleteSkills);
    }
}


