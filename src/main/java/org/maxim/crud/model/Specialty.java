package org.maxim.crud.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxim.crud.enums.SpecialtyStatus;

@Builder
@Getter
@Setter
@ToString
public class Specialty {
    private Long id;
    private String name;
    private SpecialtyStatus specialtyStatus;
}
