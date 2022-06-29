package eu.nnn4.acleaneva.io.out.rdb.tests;

import eu.nnn4.acleaneva.io.out.rdb.AccountCustomRepo;
import eu.nnn4.acleaneva.io.out.rdb.AccountSpringRepo;
import eu.nnn4.acleaneva.io.out.rdb.AddressSpringRepo;
import eu.nnn4.acleaneva.io.out.rdb.IAccountCustomRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
class IoOutRdbReposTest {

    @Autowired(required = false)
    private AccountCustomRepo accountCustomRepo;
    @Autowired
    private AccountSpringRepo accountSpringRepo;
    @Autowired
    private AddressSpringRepo addressSpringRepo;
    @Autowired
    private IAccountCustomRepo iaccountCustomRepo;

    @Test
    void springReposAreLoaded() {
        assertThat(accountSpringRepo).isNotNull();
        assertThat(addressSpringRepo).isNotNull();
        assertThat(accountCustomRepo).isNull();
        assertThat(iaccountCustomRepo).isNotNull();
    }

    @Value("${spring.datasource.url}")
    private String url;

    @Test
    void whenRun_h2propertiesLoaded() {
        log.info("datasource.url: " + url);
        Assertions.assertNotNull(url);
    }
}