package eu.nnn4.acleaneva.app.in.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = {"eu.nnn4.acleaneva.app.out.rdb", "eu.nnn4.acleaneva.app.service" })
public class AppInWebConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppInWebConfig.class);

    @PostConstruct
    public void postConstruct(){
        logger.info("AppInWebConfig, PACKAGES LOADED!");
    }
}
