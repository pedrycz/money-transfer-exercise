package cz.pedry.moneytransfer.service;

import cz.pedry.moneytransfer.model.Transfer;
import cz.pedry.moneytransfer.repository.TransferRepository;

public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public long save(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public Transfer findById(long id) {
        return transferRepository.findById(id);
    }

}
