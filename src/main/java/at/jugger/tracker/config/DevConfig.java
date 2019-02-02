package at.jugger.tracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("default")
@PropertySource({"classpath:application.properties", "classpath:application-dev.properties"})
public class DevConfig {
}
