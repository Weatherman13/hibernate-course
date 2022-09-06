package com.example.hibernatecourse.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "company")
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

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

    // TODO: 06.09.2022 optional - for  "true" left join or "false" inner join
    // TODO: 06.09.2022 fetch - для получения сущности со всеми вложенными объектами "EAGER",
    // TODO: 06.09.2022 а для получения сущности без вложенных объектов и подгрузки их только при необходимости "LAZY"
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

}
