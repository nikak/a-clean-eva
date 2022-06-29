import eu.nnn4.acleaneva.domain.Address;
import eu.nnn4.acleaneva.domain.datagenerator.DataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DataGeneratorTest {
    @Test
    public void whenGenerateAddresses_thenReturnSize() {
        int num = 2;
        List<Address> addresses = DataGenerator.generateAddressesWithName(num, "Serdo Loga");
        Assertions.assertEquals(addresses.size(), num);
    }

}
