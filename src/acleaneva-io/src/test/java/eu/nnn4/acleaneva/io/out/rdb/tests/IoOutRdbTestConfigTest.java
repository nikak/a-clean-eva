package eu.nnn4.acleaneva.io.out.rdb.tests;

import eu.nnn4.test.helper.context.ContextInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = IoOutRdbTestConfig.class)
class IoOutRdbTestConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void configurationLoads() {
        ContextInfo.printLoadedBeans(applicationContext);
    }

    @Test
    void whenIoOutRdbConfig_beansLoaded() {
        final String mpackage = this.getClass().getPackageName();
        ContextInfo.printContextLoaded(applicationContext, mpackage);
        Assertions.assertTrue(ContextInfo.loadedFilteredBeansGreaterThan(applicationContext, mpackage, 0));
    }
}