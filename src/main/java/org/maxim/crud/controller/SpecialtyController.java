package org.maxim.crud.controller;

import lombok.RequiredArgsConstructor;
import org.maxim.crud.enums.SpecialtyStatus;
import org.maxim.crud.model.Specialty;
import org.maxim.crud.repository.SpecialtyRepository;

import java.util.List;
@RequiredArgsConstructor
public class SpecialtyController {

        private final SpecialtyRepository specialtyRepository;
        public Specialty createSpecialty(String name, SpecialtyStatus speciatlyStatus){
            Specialty newSpecialty = Specialty.builder()
                    .name(name)
                    .specialtyStatus(speciatlyStatus)
                    .build();

            return specialtyRepository.save(newSpecialty);
        }

        public Specialty getSpecialtyById(Long id){
            return specialtyRepository.getById(id);
        }

        public List< Specialty> getAllSpecialty(){
            return specialtyRepository.getAll();
        }

        public Specialty updateSpecialty(Long id, String name, SpecialtyStatus SpecialtyStatus){
            Specialty updateSpecialty =  Specialty.builder()
                    .name(name)
                    .specialtyStatus(SpecialtyStatus)
                    .build();

            updateSpecialty.setId(id);

            return  specialtyRepository.update(updateSpecialty);
        }

        public void deleteSpecialty(Long id){
            specialtyRepository.deleteById(id);
        }
    }


