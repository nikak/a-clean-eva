package eu.nnn4.acleaneva.io.out.rdb.tests;

import eu.nnn4.acleaneva.domain.Address;
import eu.nnn4.acleaneva.domain.datagenerator.DataGenerator;
import eu.nnn4.acleaneva.io.out.rdb.AccountCustomRepo;
import eu.nnn4.acleaneva.io.out.rdb.AccountSpringRepo;
import eu.nnn4.acleaneva.io.out.rdb.AddressSpringRepo;
import eu.nnn4.acleaneva.io.out.rdb.IAccountCustomRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@TestInstance(Lifecycle.PER_CLASS)
@Slf4j
@DataJpaTest
class IoOutRdbAddressRepoTest {

    @Autowired(required = false)
    private AccountCustomRepo accountCustomRepo;
    @Autowired
    private AccountSpringRepo accountSpringRepo;
    @Autowired
    private AddressSpringRepo addressSpringRepo;
    @Autowired
    private IAccountCustomRepo iaccountCustomRepo;

    private static List<Address> addresses = new ArrayList<>();

    @BeforeAll
    static void createData() {
        addresses = DataGenerator.generateAddressesWithName(3, "Sara John");
        log.info("BeforeAll==============");
        log.info(Arrays.toString(addresses.toArray()));
    }

    @Order(1)
    @Test
    void whenNoAddresses_thenReturnNull() {
        int num = addressSpringRepo.findAll().size();
        Assertions.assertEquals(0, num);
    }

    @Order(2)
    @Test
    void whenSaveAddresses_thenReturnSize() {
        addressSpringRepo.saveAll(addresses);
        List<Address> addr = addressSpringRepo.findAll();
        Assertions.assertEquals(addresses.size(), addr.size());
        for (int i = 0; i < addr.size(); i++) {
            Assertions.assertNotNull(addr.get(i).getAddressId());
            log.info("i=" + i + ", " + addr.get(i));
        }
        whenFindAddresses_thenReturnSize();
    }

    void whenFindAddresses_thenReturnSize() {
        String nameTolook = addresses.get(0).getFullName();
        log.info("nameTolook: " + nameTolook);
        List<Address> addr = addressSpringRepo.findByFullName(nameTolook);
        Assertions.assertEquals(addresses.size(), addr.size());
    }

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