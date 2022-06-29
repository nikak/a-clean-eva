package eu.nnn4.acleaneva.app.out.rdb;

//import org.jmolecules.architecture.layered.InterfaceLayer;

import eu.nnn4.acleaneva.domain.Account;
import eu.nnn4.acleaneva.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

//@InterfaceLayer
public interface IManageAccount {
    List<Account> findAll();
    Page<Account> findAll(Pageable pageable);
    List<Account> findAllSortByCreatedOn();

    Optional<Account> findById(Long id);

    List<Account> findByAddress_City(String city);
    Page<Account> findByAddress_City(String city, Pageable pageable);

    void save(Account entity);
    void update(Account entity);
    void deleteById(Long id);
    void saveAll(Iterable<Account> entities);

    void deleteByIdAddress(Long id, Address address);
    void addByIdAddress(Long id, Address address);

    List<Account> findAccountByEmails(List<String> emails);
}
