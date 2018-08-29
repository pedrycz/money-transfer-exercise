package cz.pedry.moneytransfer.service;

import cz.pedry.moneytransfer.model.Account;

public interface AccountService {

    int create();

    Account findById(int id);

}
