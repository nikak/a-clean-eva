package eu.nnn4.acleaneva.io.out.rdb;

import eu.nnn4.acleaneva.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressSpringRepo extends JpaRepository<Address, Long> {
    public List<Address> findByFullName(String name);
}
