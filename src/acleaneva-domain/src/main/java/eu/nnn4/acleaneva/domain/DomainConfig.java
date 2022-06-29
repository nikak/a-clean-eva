package eu.nnn4.acleaneva.domain;

import eu.nnn4.test.helper.context.ContextInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@ComponentScan(basePackages = {"eu.nnn4.acleaneva.domain"})
public class DomainConfig {

    private static final Logger logger = LoggerFactory.getLogger(DomainConfig.class);
    private final ApplicationContext context;

    @PostConstruct
    public void postConstruct(){
        logger.info("DomainConfig, PACKAGES LOADED!");
    }

}
