package ru.konoplev.repository;

import org.springframework.stereotype.Service;
import ru.konoplev.dao.FilmDAO;
import ru.konoplev.model.Film;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmDAO filmDAO;

    public FilmServiceImpl(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Override
    public List<Film> getAll() {
        return filmDAO.allFilm();
    }

    @Override
    public Film getById(long id) {
        return filmDAO.getById(id);
    }

    @Override
    public void edit(Film film) {
        filmDAO.editFilm(film);
    }

    @Override
    public void delete(Long id) {
        filmDAO.deleteFilm(id);
    }

    @Override
    public void create(Film film) {
        filmDAO.addFilm(film);
    }
}
