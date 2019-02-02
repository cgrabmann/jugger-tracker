package at.jugger.tracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource({"classpath:application.properties", "file:/home/jugger/config/application.properties"})
public class ProdConfig {
}
