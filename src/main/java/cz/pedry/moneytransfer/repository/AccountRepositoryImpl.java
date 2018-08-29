package cz.pedry.moneytransfer.repository;

import cz.pedry.moneytransfer.model.Account;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class AccountRepositoryImpl extends AbstractDAO<Account> implements AccountRepository {

    public AccountRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public int save(Account account) {
        return (Integer) currentSession().save(account);
    }

    @Override
    public Account findById(int id) {
        return currentSession().get(Account.class, id);
    }

}
