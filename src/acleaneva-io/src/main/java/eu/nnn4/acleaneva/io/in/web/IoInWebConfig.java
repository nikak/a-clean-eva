package eu.nnn4.acleaneva.io.in.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = {"eu.nnn4.acleaneva.io.in.web"})
public class IoInWebConfig {

    private static final Logger logger = LoggerFactory.getLogger(IoInWebConfig.class);

    @PostConstruct
    public void postConstruct(){
        logger.info("IoInWebConfig, PACKAGES LOADED!");
    }
}
