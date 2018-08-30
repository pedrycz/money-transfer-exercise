package cz.pedry.moneytransfer.resource;

import cz.pedry.moneytransfer.model.Transfer;
import cz.pedry.moneytransfer.service.TransferService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class TransferResourceTest {

    private static final long EXAMPLE_LONG = 1L;
    private static final String VALID_TRANSFER = "{\"from\": {\"id\": 1}, \"to\": {\"id\": 2}, \"amount\": 500}";
    private static final String INVALID_TRANSFER = "{\"from\": {\"id\": 1}, \"to\": {\"id\": 2}, \"amount\": 0}";

    private static final TransferService transferService = mock(TransferService.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new TransferResource(transferService))
            .build();

    @Test
    public void shouldCreateNewValidTransfer() {
        doReturn(EXAMPLE_LONG).when(transferService).save(any(Transfer.class));

        Response response = resources.client().target("/transfer").request().post(Entity.json(VALID_TRANSFER));

        assertThat(response.readEntity(Long.class)).isEqualTo(EXAMPLE_LONG);
    }

}
