package com.example.hibernatecourse;

import com.example.hibernatecourse.entity.Company;
import com.example.hibernatecourse.entity.PersonalData;
import com.example.hibernatecourse.entity.User;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) throws HibernateException {

        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(User.class);
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var company = Company.builder().name("Sitronics").build();

            User user = User.builder()
                    .username("petr132@gmail.com")
                    .data(PersonalData.builder()
                            .firstname("Alexey")
                            .lastname("Serov")
                            .birthDate(LocalDate.of(2000, 1, 2))
                            .build())
                    .company(company)
                    .build();
            var user1 = session.get(User.class, 1l);
            session.evict(user1);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
