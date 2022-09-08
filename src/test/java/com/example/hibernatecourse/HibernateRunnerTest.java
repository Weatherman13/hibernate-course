package com.example.hibernatecourse;

import com.example.hibernatecourse.entity.Company;
import com.example.hibernatecourse.entity.User;
import com.example.hibernatecourse.utils.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

class HibernateRunnerTest {

    @Test
    void deleteCompany(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        var company = session.get(Company.class, 2);

        session.delete(company);

        session.getTransaction().commit();
    }

    @Test
    void addUserToNewCompany(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        var company = Company.builder()
                        .name("Facebook")
                                .build();

        var user = User.builder()
                        .username("sveta123@bb.com")
                                .build();

//        user.setCompany(company);
//        company.getUsers().add(user);
        company.addUser(user);

        session.save(company);
        System.out.println(" ");

        session.getTransaction().commit();
    }
    @Test
    void OneToMany(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        var company = session.get(Company.class, 1);

        System.out.println(" ");

        session.getTransaction().commit();
    }

    @Test
    void checkReflectionApi() {

        User user = User.builder()
                .build();

        String sql = """
                insert
                into
                %s
                (%s)
                values
                (%s)
                """;
        var tableNames = ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] declaredFields = user.getClass().getDeclaredFields();

        var columnNames = Arrays.stream(declaredFields)
                .map(field -> ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(joining(", "));

        var columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        System.out.println(sql.formatted(tableNames, columnNames, columnValues));

    }
}