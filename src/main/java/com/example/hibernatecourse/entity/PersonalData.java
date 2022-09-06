package com.example.hibernatecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonalData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String lastname;
    private String firstname;
    private LocalDate birthDate;

}
