package org.maxim.crud.repository.gson;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.maxim.crud.enums.SkillStatus;
import org.maxim.crud.enums.SpecialtyStatus;
import org.maxim.crud.model.Skill;
import org.maxim.crud.model.Specialty;
import org.maxim.crud.repository.SpecialtyRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {
    private final Gson gson = new Gson();
    private final String FILE_PATH = "src/main/resources/specialties.json";

    private List<Specialty> loadSpecialtys(){
        try(Reader specialtyreader = new FileReader(FILE_PATH)){
            Type type = new TypeToken<List<Specialty>>(){}.getType();
            List<Specialty> specialtys = gson.fromJson(specialtyreader, type);

            if(specialtys == null){
                specialtys = new ArrayList<>();
            }

            return specialtys;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private void saveSkills(List<Specialty> specialtys){
        try(FileWriter skillswriter = new FileWriter(FILE_PATH)){
            gson.toJson(specialtys, skillswriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Specialty getById(Long id) {
        return loadSpecialtys()
                .stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Specialty> getAll() {
        return loadSpecialtys();
    }

    @Override
    public Specialty save(Specialty specialty) {
        List<Specialty> specialtys = loadSpecialtys();

        specialty.setId(IdGenerator.generatedId(specialtys, Specialty::getId));

        specialtys.add(specialty);
        saveSkills(specialtys);

        return specialty;
    }
    @Override
    public Specialty update(Specialty updateSpecialty) {
        List<Specialty> specialtys = loadSpecialtys();
        List<Specialty> updateSpecialtys = specialtys
                .stream()
                .map(d -> {
                    if (d.getId().equals(updateSpecialty.getId())){
                        return updateSpecialty;
                    }
                    return d;
                }).toList();

        saveSkills(updateSpecialtys);

        return updateSpecialty;
    }

    @Override
    public void deleteById(Long id) {
        List<Specialty> specialtys = loadSpecialtys();

        List<Specialty> deleteSpecialtys = specialtys
                .stream()
                .map(specialty -> {
                    if(specialty.getId().equals(id)){
                        specialty.setSpecialtyStatus(SpecialtyStatus.DELETED);
                    }
                    return specialty;
                }).toList();

        saveSkills(deleteSpecialtys);
    }
}
