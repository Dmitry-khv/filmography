package ru.konoplev.repository;

import ru.konoplev.model.Film;

import java.util.List;

public interface FilmService {
    List<Film> getAll();
    Film getById(long id);
    void edit(Film film);
    void delete(Long id);
    void create(Film film);
}
