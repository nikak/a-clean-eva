package eu.nnn4.acleaneva.startup.spring;

import eu.nnn4.acleaneva.app.in.web.AppInWebConfig;
import eu.nnn4.acleaneva.app.out.rdb.AppOutRdbConfig;
import eu.nnn4.acleaneva.io.in.web.IoInWebConfig;
import eu.nnn4.acleaneva.io.out.rdb.config.IoOutRdbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Import(value = {
        //DomainConfig.class,
        AppOutRdbConfig.class, IoOutRdbConfig.class,
        AppInWebConfig.class, IoInWebConfig.class})
@Configuration
//@ConditionalOnProperty(value = "eventstarter.enabled", havingValue = "true")
//@ConditionalOnClass(name = "io.reflectoring.KafkaConnector")
@EnableConfigurationProperties(AcleanevaProperties.class)
public class SpringConfig {

    //    @Autowired
//    AnnotationConfigApplicationContext acaCtx;
    @Autowired
    private ApplicationContext appContext;


//    @Bean
//    @ConditionalOnProperty(value = "acleaneva.activeDb", havingValue = "h2")
//    public getH2DataSourceConfig() {
//        return new DataSourceConfig();
//    }
//
//    @Bean
//    @ConditionalOnProperty(value = "acleaneva.activeDb", havingValue = "postgres")
//    public getDataSourceConfig() {
//        return new DataSourceConfig();
//    }

//    @FunctionalInterface
//    public static interface GreeterService {
//        String greeting();
//    }
//      https://stackoverflow.com/questions/44990594/propertysource-java-io-filenotfoundexception
//    @Bean
//    @ConditionalOnProperty("hello")
//    public GreeterService helloService() {
//        return () -> "hello";
//    }
}

//https://github.com/thombergs/code-examples/blob/master/spring-boot/modular/booking-module/README.md
//https://github.com/thombergs/code-examples/blob/master/spring-boot/starter/README.md