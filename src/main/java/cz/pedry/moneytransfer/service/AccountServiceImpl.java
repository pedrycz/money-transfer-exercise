package cz.pedry.moneytransfer.service;

import cz.pedry.moneytransfer.model.Account;
import cz.pedry.moneytransfer.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public int create() {
        return accountRepository.save(new Account());
    }

    @Override
    public Account findById(int id) {
        return accountRepository.findById(id);
    }

}
