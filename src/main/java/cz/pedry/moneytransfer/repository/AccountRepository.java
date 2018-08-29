package cz.pedry.moneytransfer.repository;

import cz.pedry.moneytransfer.model.Account;

public interface AccountRepository {

    int save(Account account);

    Account findById(int id);

}
