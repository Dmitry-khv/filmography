package ru.konoplev.dao;

import ru.konoplev.model.Film;

import java.util.List;

public interface FilmDAO {
    List<Film> allFilm();
    void addFilm(Film film);
    void deleteFilm(Film film);
    void editFilm(Film film);
    Film getById(long id);
}
