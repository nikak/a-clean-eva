package eu.nnn4.acleaneva.domain.datagenerator;

import eu.nnn4.acleaneva.domain.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static List<Address> generateAddressesWithName(int number, String name) {
        List<Address> addresses = new ArrayList<>();
        Random rand = new Random();
        String nameFull = (name != null) ? name : "Larry Lint" + rand.nextInt(1000);
        for (int i = 0; i < number; i++) {
            addresses.add(generateAddressWithName(name, rand));
        }
        return addresses;
    }

    private static Address generateAddressWithName(String name, Random rand) {
        return new Address(null, name,
                "Street_" + rand.nextInt(100),
                "City_" + rand.nextInt(100),
                "ZIP" + rand.nextInt(100),
                "De",
                rand.nextBoolean() ? null : "Additional_" + rand.nextInt(100));
    }


}
