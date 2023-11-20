package org.maxim.crud.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxim.crud.enums.SkillStatus;

@Builder
@Getter
@Setter
@ToString
public class Skill {
    private Long id;
    private String name;
    private SkillStatus skillStatus;
}
