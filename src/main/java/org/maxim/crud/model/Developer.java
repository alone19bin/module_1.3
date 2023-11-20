package org.maxim.crud.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxim.crud.enums.DeveloperStatus;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Skill> skills;
    private String specialty;
    private DeveloperStatus developerStatus;

}

