package ru.konoplev.sessionmanager;


public interface SessionManager extends AutoCloseable {
    void beginSession();
    void commitSession();
    void rollBackSession();
    void close();
    DataBaseSession getCurrentSession();
}
