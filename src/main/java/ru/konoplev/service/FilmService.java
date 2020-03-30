package ru.konoplev.service;

import ru.konoplev.model.Film;

import java.util.List;

public interface FilmService {
    List<Film> getAll();
    void addFilm(Film film);
    void editFilm(Film film);
    void deleteFilm(Long id);
    Film getById(Long id);
}
