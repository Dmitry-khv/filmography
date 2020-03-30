package ru.konoplev.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import ru.konoplev.model.Film;

@Configuration
@ImportResource({"classpath:WEB-INF/config/hibernate.cfg.xml"})
public class HibernateConfig {

    private static final String HIBERNATE_CONFIG = "WEB-INF/config/hibernate.cfg.xml";

    @Bean
    public SessionFactory sessionFactory() {
        return HibernateUtils.buildSessionFactory(HIBERNATE_CONFIG, Film.class);
    }
}
