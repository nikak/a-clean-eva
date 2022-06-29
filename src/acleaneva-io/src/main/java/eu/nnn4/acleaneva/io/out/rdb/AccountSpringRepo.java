package eu.nnn4.acleaneva.io.out.rdb;

import eu.nnn4.acleaneva.domain.Account;
import eu.nnn4.acleaneva.domain.Address;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@Repository
@DynamicUpdate
public interface AccountSpringRepo extends JpaRepository<Account, Long>,
        IAccountCustomRepo{
    List<Account> findByAddresses_City(String city);
    Page<Account> findByAddresses_City(String city,Pageable pageable);
    List<Account> findByOrderByCreatedDateAsc();

    @Modifying
    @Query("UPDATE Account u set u.firstName = ?1, u.secondName = ?2 where u.accountId = ?3")
    void setFirstNameAndSecondNameById(String firstname, String secondname, Long userId);

    @Query(value = "SELECT u FROM Account u ORDER BY u.accountId")
    Page<Account> findAllAccounts(Pageable pageable);

    @Query("SELECT u FROM Account u WHERE u.accountType = :atype AND (u.secondName = :name OR u.firstName LIKE '%name%')")
    Optional<Account> findByAccountTypeAndName(@Param("atype") Integer accountType,
                                                      @Param("name") String name);

    @Query("SELECT u FROM Account u JOIN u.addresses a WHERE (u.addresses.size > 0) AND (a.city = :city)")
    List<Account> findByAddresses_City_jqpl(String city);
}
