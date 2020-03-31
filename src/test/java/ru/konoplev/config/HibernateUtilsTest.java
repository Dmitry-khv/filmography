package ru.konoplev.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.konoplev.model.Film;
import ru.konoplev.sessionmanager.DataBaseSessionHibernate;
import ru.konoplev.sessionmanager.SessionManagerException;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class HibernateUtilsTest {
    private static final String HIBERNATE_CONFIG = "WEB-INF/config/hibernate.cfg.xml";
    private Session session;
    private SessionFactory sessionFactory;
    private DataBaseSessionHibernate dataBaseSessionHibernate;


    @BeforeEach
    void setUp() {
        sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CONFIG, Film.class);
        dataBaseSessionHibernate = new DataBaseSessionHibernate(sessionFactory.openSession());
    }


    @Test
    @DisplayName("Сессии создаются успешно")
    void buildSessionFactory() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.doWork(connection -> assertFalse(connection.isClosed()));
    }
    @Test
    void transactionIsActive() {
        session = sessionFactory.getCurrentSession();
        DataBaseSessionHibernate dataBaseSessionHibernate = new DataBaseSessionHibernate(session);
        assertNotNull(dataBaseSessionHibernate.getTransaction());
//        session.beginTransaction();
    }


    @AfterEach
    void tearDown() {
        sessionFactory.close();
    }


}