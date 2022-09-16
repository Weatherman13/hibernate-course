package com.example.hibernatecourse;

import com.example.hibernatecourse.entity.Company;
import com.example.hibernatecourse.entity.Profile;
import com.example.hibernatecourse.entity.User;
import com.example.hibernatecourse.utils.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Hibernate;
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
    void checkOneToOne() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {

            session.beginTransaction();

            var user = User.builder()
                    .username("test2@ml.com")
                    .build();
            var profile = Profile.builder()
                    .language("ru")
                    .street("Kolasa 18")
                    .build();

           profile.setUser(user);

           session.save(user);


            session.getTransaction().commit();

        }
    }

    @Test
    void checkOrhanRemoval() {

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var company = session.get(Company.class, 1);
            // TODO: 16.09.2022 OrphanRemoval можем удалять сущность из базы не только с помощью сервиса, но
            // TODO: 16.09.2022 OrphanRemoval но и из коллекции в другой сущности
            company.getUsers().removeIf(user -> user.getId().equals(1l));

            session.getTransaction().commit();
        }
    }

    @Test
    void checkLazyInitialisation() {
        Company company = null;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            company = session.get(Company.class, 1);
//            Hibernate.initialize(company.getUsers());

            session.getTransaction().commit();
        }
        // TODO: 16.09.2022 Выпадет ошибка, т.к. мы пытаемся получить объект которого нет. Там прокси Hibernate-а
        var users = company.getUsers();
        System.out.println(users.size());
    }

    @Test
    void checkLazyInitialisationProxy() {
        Company company = null;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            company = session.getReference(Company.class, 1);
//            Hibernate.initialize(company.getUsers());

            session.getTransaction().commit();
        }
        System.out.println(company);
    }

    @Test
    void OneToMany() {
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