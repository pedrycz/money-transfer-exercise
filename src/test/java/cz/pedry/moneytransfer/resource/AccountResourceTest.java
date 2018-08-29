package cz.pedry.moneytransfer.resource;

import cz.pedry.moneytransfer.model.Account;
import cz.pedry.moneytransfer.service.AccountService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AccountResourceTest {

    private static final int EXAMPLE_INT = 1;
    private static final String SERIALIZED_ACCOUNT = "{\"id\":1,\"transfersFrom\":[],\"transfersTo\":[],\"balance\":0}";

    private static final AccountService accountService = mock(AccountService.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new AccountResource(accountService))
            .build();

    @Test
    public void shouldCreateNewAccount() {
        doReturn(EXAMPLE_INT).when(accountService).create();

        Response response = resources.client().target("/account").request().post(Entity.text(""));

        assertThat(response.readEntity(Integer.class)).isEqualTo(EXAMPLE_INT);
    }

    @Test
    public void shouldGetExistingAccount() {
        Account account = new Account();
        account.setId(EXAMPLE_INT);
        doReturn(account).when(accountService).findById(EXAMPLE_INT);

        Response response = resources.client().target("/account/" + EXAMPLE_INT).request().get();

        assertThat(response.readEntity(String.class)).isEqualTo(SERIALIZED_ACCOUNT);
    }

    @Test
    public void shouldReturnNoContentWhenAccountDoesNotExist() {
        doReturn(null).when(accountService).findById(EXAMPLE_INT);

        Response response = resources.client().target("/account/" + EXAMPLE_INT).request().get();

        assertThat(response.getStatus()).isEqualTo(204);
    }

}
