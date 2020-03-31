package ru.konoplev.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.konoplev.dao.FilmDAO;
import ru.konoplev.model.Film;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmDAO filmDAO;

    public FilmServiceImpl(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Transactional
    @Override
    public List<Film> allFilms() {
        return filmDAO.allFilm();
    }

    @Transactional
    @Override
    public void addFilm(Film film) {
        filmDAO.addFilm(film);
    }

    @Transactional
    @Override
    public void editFilm(Film film) {
        filmDAO.editFilm(film);
    }

    @Transactional
    @Override
    public void deleteFilm(Film film) {
        filmDAO.deleteFilm(film);
    }

    @Transactional
    @Override
    public Film getById(Long id) {
        return filmDAO.getById(id);
    }
}
