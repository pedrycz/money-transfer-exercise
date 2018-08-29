package cz.pedry.moneytransfer.service;

import cz.pedry.moneytransfer.model.Account;
import cz.pedry.moneytransfer.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    private static final int EXAMPLE_INT = 1;

    @Mock
    private Account account;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testSave() {
        doReturn(EXAMPLE_INT).when(accountRepository).save(any());

        assertThat(accountService.create()).isEqualTo(EXAMPLE_INT);
    }

    @Test
    public void testFindById() {
        doReturn(account).when(accountRepository).findById(EXAMPLE_INT);

        assertThat(accountService.findById(EXAMPLE_INT)).isEqualTo(account);
    }

}
