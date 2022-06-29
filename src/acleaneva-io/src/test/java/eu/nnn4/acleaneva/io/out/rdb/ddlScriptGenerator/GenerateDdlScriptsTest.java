package eu.nnn4.acleaneva.io.out.rdb.ddlScriptGenerator;

import eu.nnn4.acleaneva.io.out.rdb.config.IoOutRdbConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@Slf4j
//@ConditionalOnProperty(value = "${acleaneva.generateDdlScripts}", havingValue = "true")
@TestPropertySource(locations = "classpath:ddlscripts.properties",
                    properties = {
                            "acleaneva.activeDb=h2",
                            "spring.jpa.generate-ddl=true",
                            "spring.jpa.hibernate.ddl-auto=create-drop",
                            "spring.flyway.enabled=false",
                            "spring.jpa.show-sql=true",
                            "POSTGRES_URL=jdbc:postgresql://localhost:5433/acleaneva_database",
                            "POSTGRES_PASSWORD=postgres",
                            "POSTGRES_USER=postgres"
                    }
)
@SpringBootConfiguration
@EnableAutoConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //when we need non-static @BeforeAll
@SpringBootTest(classes = IoOutRdbConfig.class)
class GenerateDdlScriptsTest extends IoOutRdbConfig {

    @Value("${autogen_directory}")
    private String autogenDir;

    @Test
    void whenIoOutRdbTestH2Config_beansLoaded() {
        Assertions.assertNotNull(autogenDir);
    }
}
