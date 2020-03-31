package ru.konoplev.sessionmanager;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataBaseSessionHibernate implements DataBaseSession {
    private final Session session;
    private final Transaction transaction;

    public DataBaseSessionHibernate(Session session) {
        this.session = session;
        transaction = session.beginTransaction();
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void close() {
        if(transaction.isActive())
            transaction.commit();
        session.close();
    }
}
