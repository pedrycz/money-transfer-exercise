package cz.pedry.moneytransfer.repository;

import cz.pedry.moneytransfer.model.Transfer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TransferRepositoryImplTest {

    private static final long EXAMPLE_LONG = 1L;

    @Mock
    private Transfer transfer;

    @Mock
    private Session session;

    @Mock
    private SessionFactory sessionFactory;

    @InjectMocks
    private TransferRepositoryImpl transferRepository;

    @Before
    public void init() {
        doReturn(session).when(sessionFactory).getCurrentSession();
    }

    @Test
    public void testSave() {
        doReturn(EXAMPLE_LONG).when(session).save(transfer);

        assertThat(transferRepository.save(transfer)).isEqualTo(EXAMPLE_LONG);
    }

    @Test
    public void testFindById() {
        doReturn(transfer).when(session).get(Transfer.class, EXAMPLE_LONG);

        assertThat(transferRepository.findById(EXAMPLE_LONG)).isEqualTo(transfer);
    }

}
