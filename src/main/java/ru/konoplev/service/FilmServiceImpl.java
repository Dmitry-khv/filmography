package ru.konoplev.service;

import org.springframework.stereotype.Service;
import ru.konoplev.dao.FilmDAO;
import ru.konoplev.model.Film;

import java.util.List;


public class FilmServiceImpl implements FilmService {
    private final FilmDAO filmDAO;

    public FilmServiceImpl(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Override
    public List<Film> allFilms() {
        return filmDAO.allFilm();
    }

    @Override
    public void addFilm(Film film) {
        filmDAO.addFilm(film);
    }

    @Override
    public void editFilm(Film film) {
        filmDAO.editFilm(film);
    }

    @Override
    public void deleteFilm(Long id) {
        filmDAO.deleteFilm(id);
    }

    @Override
    public Film getById(Long id) {
        return filmDAO.getById(id);
    }
}
