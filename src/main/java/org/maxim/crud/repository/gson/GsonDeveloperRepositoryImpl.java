package org.maxim.crud.repository.gson;

import com.google.gson.reflect.TypeToken;
import org.maxim.crud.controller.DeveloperController;
import org.maxim.crud.enums.DeveloperStatus;
import org.maxim.crud.model.Developer;
import org.maxim.crud.model.Skill;
import org.maxim.crud.repository.DeveloperRepository;
import com.google.gson.Gson;
import org.maxim.crud.repository.SkillRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

    private final Gson gson = new Gson();
    private final String FILE_PATH = "src/main/resources/developers.json";


    private void saveDeveloper(List<Developer> developers){
        try(FileWriter developerFileWriter = new FileWriter(FILE_PATH)){
            gson.toJson(developers, developerFileWriter);
        } catch (IOException e) {
            System.out.println("Error");;
        }
    }

    private List<Developer> loadDevelopers() {
        try(Reader developerFileReader = new FileReader(FILE_PATH)){
            Type type = new TypeToken<List<Developer>>(){}.getType();
            List<Developer> developers = gson.fromJson(developerFileReader, type);
            if(developers == null){
                developers = new ArrayList<>();
            }

            return developers;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }


    @Override
    public Developer getById(Long id) {
        return loadDevelopers().stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Developer> getAll() {
        return loadDevelopers();
    }

    @Override
    public Developer save(Developer developer) {
        List<Developer> developers = loadDevelopers();
        developer.setId(IdGenerator.generatedId(developers, Developer::getId));
        developers.add(developer);
        saveDeveloper(developers);
        return developer;
    }

    @Override
    public Developer update(Developer updateDeveloper) {
        List<Developer> currentDevelopers = loadDevelopers();
        List<Developer> updatedDevelopers = currentDevelopers
                .stream()
                .map(haveDeveloper -> {
                    if (haveDeveloper.getId().equals(updateDeveloper.getId())) {
                        return updateDeveloper;
                    }
                    return haveDeveloper;
                }).toList();

        saveDeveloper(updatedDevelopers);
        return updateDeveloper;
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> developers = loadDevelopers()
                .stream().peek(d -> {
                    if(d.getId().equals(id)) {
                        d.setDeveloperStatus(DeveloperStatus.DELETED);
                    }
                }).toList();
        saveDeveloper(developers);
    }
}
