package ru.konoplev.generator;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class FilmIdGenerator implements IdGenerator {
    private static AtomicLong FILM_ID = new AtomicLong(0);
    @Override
    public long getId() {
        return FILM_ID.incrementAndGet();
    }
}
