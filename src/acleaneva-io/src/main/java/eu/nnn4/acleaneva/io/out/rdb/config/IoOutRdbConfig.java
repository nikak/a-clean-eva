package eu.nnn4.acleaneva.io.out.rdb.config;

import eu.nnn4.acleaneva.domain.DomainConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.io.IOException;

@EnableJpaAuditing
@EntityScan("eu.nnn4.acleaneva.domain")
@Configuration
@Import(value = {DomainConfig.class})
@EnableJpaRepositories(
        basePackages = "eu.nnn4.acleaneva.io.out.rdb"
)
@PropertySource(ignoreResourceNotFound = false,
                value = {"classpath:ioRdb.properties",
                        "classpath:${acleaneva.activeDb}.properties",
                        "classpath:tomcatPool.properties"
                })
@EnableTransactionManagement
@ComponentScan(basePackages = {"eu.nnn4.acleaneva.io.out.rdb"})
public class IoOutRdbConfig {

    private static final Logger log = LoggerFactory.getLogger(IoOutRdbConfig.class);

    @PostConstruct
    public void postConstruct() {
        log.info("IoOutRdbConfig, PACKAGES LOADED!");
    }

    @Autowired
    private ConfigurableEnvironment env;

    @Order(value = 1000)
    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshedEvent(ContextRefreshedEvent ev) throws IOException {
        Environment envCore = ev.getApplicationContext().getEnvironment();
        log.info("ApplicationEnvironmentPreparedEvent " +
                ev.getApplicationContext().getEnvironment().getProperty("acleaneva.generateDdl"));
        ConfigurableEnvironment cenv = env;//context.getEnvironment();
        if ("true".equals(envCore.getProperty("spring.jpa.generate-ddl"))) {
            log.info("====================generateDdl=true");
        }
        if ("true".equals(envCore.getProperty("spring.flyway.enabled"))) {
            log.info("====================spring.flyway.enabled=true");
            cenv.getPropertySources().addLast(new ResourcePropertySource(new ClassPathResource("flyway.properties")));
        }
        if ("true".equals(envCore.getProperty("acleaneva.generateDdlScripts"))) {
            log.info("====================acleaneva.generateDdlScripts=true");
        }
//        printSources(cenv);
    }


    private static void printSources(ConfigurableEnvironment env) {
        System.out.println("-- property sources --");
        for (org.springframework.core.env.PropertySource propSource : env.getPropertySources()) {
            System.out.println("name =  " + propSource.getName() + "\nsource = " + propSource
                    .getSource().getClass() + "\n");
        }
    }
}

//https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-data-jpa-query-2/src/main/java/com/baeldung/config/PersistenceConfig.java