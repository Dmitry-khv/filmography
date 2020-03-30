package ru.konoplev.sessionmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SessionManagerHibernate implements SessionManager{
    private DataBaseSessionHibernate dataBaseSessionHibernate;
    private final SessionFactory sessionFactory;

    public SessionManagerHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void beginSession() {
        try{
            dataBaseSessionHibernate = new DataBaseSessionHibernate(sessionFactory.openSession());
        } catch (Exception e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void commitSession() {
        checkSessionAndTransaction();
        try {
            dataBaseSessionHibernate.getTransaction().commit();
            dataBaseSessionHibernate.getSession().close();
        } catch (Exception e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void rollBackSession() {
        checkSessionAndTransaction();
        try{
            dataBaseSessionHibernate.getTransaction().rollback();
            dataBaseSessionHibernate.getSession().close();
        } catch (Exception e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void close() {
        if (dataBaseSessionHibernate == null)
            return;

        Session session = dataBaseSessionHibernate.getSession();
        if (session == null || !session.isConnected())
            return;

        Transaction transaction = dataBaseSessionHibernate.getTransaction();
        if (transaction == null || !transaction.isActive())
            return;

        try {
            dataBaseSessionHibernate.close();
            dataBaseSessionHibernate = null;
        } catch (Exception e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public DataBaseSession getCurrentSession() {
        checkSessionAndTransaction();
        return dataBaseSessionHibernate;
    }

    public void checkSessionAndTransaction() {
        if (dataBaseSessionHibernate == null)
            throw new SessionManagerException("DataBaseSession not open");

        Session session = dataBaseSessionHibernate.getSession();
        if (session == null || !session.isConnected())
            throw new SessionManagerException("Session not opened");

        Transaction transaction = dataBaseSessionHibernate.getTransaction();
        if(transaction == null || !transaction.isActive())
            throw new SessionManagerException("Transaction not opened");
    }
}
