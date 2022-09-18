package com.example.hibernatecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends User{

    private String projectName;

    @Builder
    public Manager(Long id, PersonalData data, String username, Role role,
                   Profile profile, Company company, Set<UserChat> userChats, String projectName) {
        super(id, data, username, role, profile, company, userChats);
        this.projectName = projectName;
    }
}
