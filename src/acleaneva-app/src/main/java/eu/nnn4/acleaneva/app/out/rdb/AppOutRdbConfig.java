package eu.nnn4.acleaneva.app.out.rdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = {"eu.nnn4.acleaneva.app.out.rdb"})
public class AppOutRdbConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppOutRdbConfig.class);

    @PostConstruct
    public void postConstruct(){
        logger.info("AppOutRdbConfig, PACKAGES LOADED!");
    }
}
