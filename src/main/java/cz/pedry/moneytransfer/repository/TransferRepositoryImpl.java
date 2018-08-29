package cz.pedry.moneytransfer.repository;

import cz.pedry.moneytransfer.model.Transfer;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class TransferRepositoryImpl extends AbstractDAO<Transfer> implements TransferRepository {

    public TransferRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public long save(Transfer transfer) {
        return (Long) currentSession().save(transfer);
    }

    @Override
    public Transfer findById(long id) {
        return currentSession().get(Transfer.class, id);
    }

}
