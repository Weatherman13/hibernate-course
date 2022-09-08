package com.example.hibernatecourse.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

// TODO: 08.09.2022 Так как Set - это интерфейс, то его стандартная реализация это HashSet, где используется equals и hashCode
// TODO: 08.09.2022 Поэтому при маппинге возникает циклическая зависимость. Именно потому мы исключили поле users для этих методов

// TODO: 08.09.2022 При таком маппинге лучше делать exclude на списке, чем на сущности
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
@Builder
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "company")
//    @JoinColumn(name = "company_id")
    private Set<User> users;

}
