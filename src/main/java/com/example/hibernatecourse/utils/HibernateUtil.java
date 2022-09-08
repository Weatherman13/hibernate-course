package com.example.hibernatecourse.utils;

import com.example.hibernatecourse.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory buildSessionFactory(){

        Configuration configuration = new Configuration();
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
