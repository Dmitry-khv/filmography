package ru.konoplev.dao;

import org.springframework.stereotype.Repository;
import ru.konoplev.generator.FilmIdGenerator;
import ru.konoplev.generator.IdGenerator;
import ru.konoplev.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FilmDAOImpl implements FilmDAO {

    private static final IdGenerator idGenerator = new FilmIdGenerator();
    private static final Map<Long, Film> films = new HashMap<>();


    @Override
    public List<Film> allFilm() {
        return new ArrayList<>(films.values());
    }

    @Override
    public void addFilm(Film film) {
        film.setId(idGenerator.getId());
        films.put(film.getId(), film);
    }

    @Override
    public void deleteFilm(Long id) {
        films.remove(id);
    }

    @Override
    public void editFilm(Film film) {
        films.put(film.getId(), film);
    }

    @Override
    public Film getById(long id) {
        return films.get(id);
    }
}
