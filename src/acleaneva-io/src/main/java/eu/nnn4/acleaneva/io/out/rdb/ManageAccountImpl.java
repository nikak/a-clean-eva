package eu.nnn4.acleaneva.io.out.rdb;

import eu.nnn4.acleaneva.app.out.rdb.IManageAccount;
import eu.nnn4.acleaneva.domain.Account;
import eu.nnn4.acleaneva.domain.Address;
import lombok.AllArgsConstructor;
//import org.jmolecules.architecture.layered.InfrastructureLayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@InfrastructureLayer
@AllArgsConstructor
public class ManageAccountImpl implements IManageAccount {
    private final AccountSpringRepo accountSpringRepo;

    @Override
    public List<Account> findAll() {
        return accountSpringRepo.findAll();
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountSpringRepo.findAllAccounts(pageable);
    }

    @Override
    public List<Account> findAllSortByCreatedOn() {
        return accountSpringRepo.findByOrderByCreatedDateAsc();
//        Page<Passenger> page = repository.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "seatNumber")));
//        List<Passenger> passengers = repository.findAll(Sort.by(Sort.Direction.ASC, "seatNumber"));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Account> findById(Long id) {
        return accountSpringRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> findByAddress_City(String str) {
        return accountSpringRepo.findByAddresses_City(str);
    }

    @Override
    public Page<Account> findByAddress_City(String city, Pageable pageable) {
        return accountSpringRepo.findByAddresses_City(city,pageable);
    }

    @Override
    public void save(Account entity) {
        accountSpringRepo.save(entity);
    }

    @Override
    public void update(Account entity) {
        accountSpringRepo.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        accountSpringRepo.deleteById(id);
    }

    @Override
    public void saveAll(Iterable<Account> entities) {
        accountSpringRepo.saveAll(entities);
    }

    @Override
    public void deleteByIdAddress(Long id, Address address) {
        accountSpringRepo.findById(id).ifPresent(account -> {
           if(deleteAddress(account,address)){
               accountSpringRepo.save(account);
           }
        });
    }

    private boolean deleteAddress(Account account,Address address){
//        if(account.getAddresses().contains(address)){
//            account.getAddresses().remove(address);
//            return true;
//        }
        return false;
    }

    @Override
    public void addByIdAddress(Long id, @Valid Address address) {
        accountSpringRepo.findById(id).ifPresent(account -> {
//            account.addAddress(address);
            accountSpringRepo.save(account);
        });
    }

    @Override
    public List<Account> findAccountByEmails(List<String> emails) {
        return accountSpringRepo.findAllByEmailsIn(emails);
    }
}
