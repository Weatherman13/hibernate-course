package com.example.hibernatecourse.entity;

import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "profile","userChats"})
@Builder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @EmbeddedId
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalData data;

    @Column(unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    // TODO: 06.09.2022 не использовать Cascade Type при child - parent зависимостях
    // TODO: 06.09.2022 в таком случае нельзя использовать отдельные каскады, но можно ALL (костыль), хотя и нежелательно
    // TODO: 06.09.2022 при inner join (optional = false) не использовать каскады, т.к. компания появилась раньше, чем юзер
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "user")
    private Set<UserChat> userChats = new HashSet<>();

}
