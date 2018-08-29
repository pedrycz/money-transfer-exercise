package cz.pedry.moneytransfer.service;

import cz.pedry.moneytransfer.model.Transfer;
import cz.pedry.moneytransfer.repository.TransferRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TransferServiceImplTest {

    private static final long EXAMPLE_LONG = 1L;

    @Mock
    private Transfer transfer;

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferServiceImpl transferService;

    @Test
    public void testSave() {
        doReturn(EXAMPLE_LONG).when(transferRepository).save(transfer);

        assertThat(transferService.save(transfer)).isEqualTo(EXAMPLE_LONG);
    }

    @Test
    public void testFindById() {
        doReturn(transfer).when(transferRepository).findById(EXAMPLE_LONG);

        assertThat(transferService.findById(EXAMPLE_LONG)).isEqualTo(transfer);
    }
}
