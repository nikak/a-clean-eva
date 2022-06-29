package eu.nnn4.acleaneva.io.out.rdb;

import eu.nnn4.acleaneva.domain.Account;

import java.util.List;
import java.util.Set;

public interface IAccountCustomRepo {
    List<Account> findAllByEmailsIn(List<String> emails);
}
