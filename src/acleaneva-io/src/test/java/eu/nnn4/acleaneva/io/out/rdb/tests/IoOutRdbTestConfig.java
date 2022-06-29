package eu.nnn4.acleaneva.io.out.rdb.tests;

import eu.nnn4.acleaneva.io.out.rdb.config.IoOutRdbConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(ignoreResourceNotFound = false,
                value = {"classpath:h2Test.properties",
                        "classpath:ioRdb.properties",
                        "classpath:${acleaneva.activeDb}.properties",
                        "classpath:tomcatPool.properties"
                })
@SpringBootConfiguration
@EnableAutoConfiguration
public
class IoOutRdbTestConfig extends IoOutRdbConfig {
}

//@AutoconfigureAfter(B.class)
//@ConditionalOnBean(B.class)
//https://stackoverflow.com/questions/34617152/how-to-re-create-database-before-each-test-in-spring