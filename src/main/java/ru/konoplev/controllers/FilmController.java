package ru.konoplev.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.konoplev.model.Film;
import ru.konoplev.repository.FilmService;

import java.util.List;

@Controller
public class FilmController {
    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping("/")
    public String allFilmsView(Model model){
        List<Film> films = filmService.getAll();
        model.addAttribute("films", films);
        return "films.html";
    }


    @GetMapping("/film/edit/{id}")
    public ModelAndView editPageView(@PathVariable("id") long id) {
        Film film = filmService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);
        return modelAndView;
    }

    @GetMapping("/add")
    public String addFilmView(Model model) {
        model.addAttribute("film", new Film());
        return "addPage.html";
    }

    @PostMapping("/add")
    public RedirectView addFilm(@ModelAttribute Film film) {
        filmService.create(film);
        return new RedirectView("/", true);
    }

    @GetMapping("/film/delete/{id}")
    public RedirectView deleteFilm(@PathVariable("id") long id) {
        filmService.delete(id);
        return new RedirectView("/", true);
    }

    @PostMapping("/film/edit")
    public RedirectView editFilm(@ModelAttribute("film") Film film) {
        filmService.edit(film);
        return new RedirectView("/", true);
    }


}
