package eu.nnn4.acleaneva.startup.spring;

import eu.nnn4.test.helper.context.ContextInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

@PropertySource(ignoreResourceNotFound = false,
                value = {"classpath:logging.properties",
//                        ,"classpath:ddlEnabled.properties",
//                        "classpath:h2.properties"
                })
@SpringBootApplication
public class SpringApp {

    private static final Logger log = LoggerFactory.getLogger(SpringApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationStartedEvent(ContextRefreshedEvent ev) {
        log.info("ContextRefreshedEvent");
        Environment env = ev.getApplicationContext().getEnvironment();
        ContextInfo.printActiveProperties((ConfigurableEnvironment) env);
    }

}
