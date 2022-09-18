package com.example.hibernatecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Programmer extends User {

    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder
    public Programmer(Long id, PersonalData data, String username, Role role,
                      Profile profile, Company company, Set<UserChat> userChats, Language language) {
        super(id, data, username, role, profile, company, userChats);
        this.language = language;
    }
}
