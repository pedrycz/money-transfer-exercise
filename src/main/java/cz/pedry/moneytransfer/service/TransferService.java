package cz.pedry.moneytransfer.service;

import cz.pedry.moneytransfer.model.Transfer;

public interface TransferService {

    long save(Transfer transfer);

    Transfer findById(long id);

}
