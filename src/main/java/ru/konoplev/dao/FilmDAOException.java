package ru.konoplev.dao;

public class FilmDAOException extends RuntimeException {
    public FilmDAOException(String msg) {
        super(msg);
    }
    public FilmDAOException(Exception e) {
        super(e);
    }
}
