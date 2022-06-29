package eu.nnn4.acleaneva.app.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application-ex.properties")
public class PropertiesConfig {

    private Environment env;

    public String getConfigValue(String configKey) {
        return env.getProperty(configKey);
    }
}