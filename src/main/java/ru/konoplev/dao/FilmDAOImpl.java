package ru.konoplev.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.konoplev.model.Film;
import ru.konoplev.sessionmanager.DataBaseSessionHibernate;
import ru.konoplev.sessionmanager.SessionManagerHibernate;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FilmDAOImpl implements FilmDAO {

    private static Logger logger = LoggerFactory.getLogger(FilmDAOImpl.class);
    private final SessionManagerHibernate sessionManager;

    public FilmDAOImpl(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }


    @Override
    public List<Film> allFilm() {
        sessionManager.beginSession();
        DataBaseSessionHibernate dbSession = sessionManager.getCurrentSession();
        try {
            Session session = dbSession.getSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Film> cq = cb.createQuery(Film.class);
            Root<Film> root = cq.from(Film.class);
            CriteriaQuery<Film> all = cq.select(root);
            TypedQuery<Film> allQuery = session.createQuery(all);
            return allQuery.getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new FilmDAOException(e);
        }
    }

    @Override
    public void addFilm(Film film) {
        sessionManager.beginSession();
        DataBaseSessionHibernate dbSession = sessionManager.getCurrentSession();
        try {
            Session session = dbSession.getSession();
            if (film.getId() > 0)
                session.merge(film);
            else
                session.persist(film);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new FilmDAOException(e);
        }
        sessionManager.commitSession();
    }

    @Override
    public void deleteFilm(Film film) {
        sessionManager.beginSession();
        DataBaseSessionHibernate dbSession = sessionManager.getCurrentSession();
        try {
            Session session = dbSession.getSession();
            session.delete(film);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new FilmDAOException(e);
        }
        sessionManager.commitSession();
    }

    @Override
    public void editFilm(Film film) {
        sessionManager.beginSession();
        DataBaseSessionHibernate dbSession = sessionManager.getCurrentSession();
        try {
            Session session = dbSession.getSession();
            session.update(film);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new FilmDAOException(e);
        }
        sessionManager.commitSession();
    }

    @Override
    public Film getById(long id) {
        sessionManager.beginSession();
        DataBaseSessionHibernate dbSession = sessionManager.getCurrentSession();
        try {
            Session session = dbSession.getSession();
            Film film = session.find(Film.class, id);
            return film;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new FilmDAOException(e);
        }
    }
}
