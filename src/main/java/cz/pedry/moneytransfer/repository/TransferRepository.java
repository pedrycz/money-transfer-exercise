package cz.pedry.moneytransfer.repository;

import cz.pedry.moneytransfer.model.Transfer;

public interface TransferRepository {

    long save(Transfer transfer);

    Transfer findById(long id);

}
