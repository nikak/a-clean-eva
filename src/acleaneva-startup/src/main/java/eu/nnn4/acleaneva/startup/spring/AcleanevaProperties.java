package eu.nnn4.acleaneva.startup.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "acleaneva")
public class AcleanevaProperties {
    private long transferThreshold = Long.MAX_VALUE;
}
