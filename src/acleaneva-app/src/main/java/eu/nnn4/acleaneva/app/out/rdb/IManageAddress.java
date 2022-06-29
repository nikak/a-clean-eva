package eu.nnn4.acleaneva.app.out.rdb;

//import org.jmolecules.architecture.layered.InterfaceLayer;

import eu.nnn4.acleaneva.domain.Account;
import eu.nnn4.acleaneva.domain.Address;

import java.util.List;
import java.util.Optional;

//@InterfaceLayer
public interface IManageAddress {
    List<Address> findAll();
    Optional<Address> findById(Long ids);
    List<Address> findByAddress_City(String str);

    void save(Address entity);
    void update(Address entity);
    void delete(Long id);
    void saveAll(Iterable<Address> entities);
}
