package eu.nnn4.acleaneva.io.out.rdb;

import eu.nnn4.acleaneva.domain.Account;
import eu.nnn4.acleaneva.domain.Account_;
import eu.nnn4.acleaneva.domain.Email;
import eu.nnn4.acleaneva.domain.Email_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AccountCustomRepo implements IAccountCustomRepo{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> findAllByEmailsIn(List<String> emails) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);

        Path<String> dd = root.join(Account_.emails).get(Email_.email);

//        Expression<Set<Email>> emailEx = root.get(Account_.emails);
//        SetJoin<Account, Email> emailsJoin = root.join(Account_.emails, JoinType.INNER);
//
//        Path<String> accountEmails = emailsJoin.get(Email_.email);
//        Fetch<Email,String> accountEmails2 = emailsJoin.fetch(Email_.email);
//        Join<Email,String> accountEmails1 = emailsJoin.join(Email_.email);
//        Expression<Collection<String>> accountEmails0 = emailsJoin.get("email");

        List<Predicate> predicates = new ArrayList<>();
        for (String email : emails) {
            predicates.add(cb.like(dd, email));
//            predicates.add(cb.equals(accountEmails, email);
//            predicates.add(cb.isMember(email, accountEmails0));
//            predicates.add(cb.like(emailEx, email));
        }
        query.select(root).distinct(true)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
