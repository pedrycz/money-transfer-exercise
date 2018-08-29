package cz.pedry.moneytransfer.repository;

import cz.pedry.moneytransfer.model.Account;
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
public class AccountRepositoryImplTest {

    private static final int EXAMPLE_INT = 1;

    @Mock
    private Account account;

    @Mock
    private Session session;

    @Mock
    private SessionFactory sessionFactory;

    @InjectMocks
    private AccountRepositoryImpl accountRepository;

    @Before
    public void init() {
        doReturn(session).when(sessionFactory).getCurrentSession();
    }

    @Test
    public void testSave() {
        doReturn(EXAMPLE_INT).when(session).save(account);

        assertThat(accountRepository.save(account)).isEqualTo(EXAMPLE_INT);
    }

    @Test
    public void testFindById() {
        doReturn(account).when(session).get(Account.class, EXAMPLE_INT);

        assertThat(accountRepository.findById(EXAMPLE_INT)).isEqualTo(account);
    }

}
