package cz.pedry.moneytransfer.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJson() throws Exception {
        Account account = new Account();
        Account contraAccount = new Account();
        Transfer transferFrom = new Transfer();
        Transfer transferTo = new Transfer();
        transferFrom.setId(1);
        transferFrom.setFrom(account);
        transferFrom.setTo(contraAccount);
        transferFrom.setAmount(ONE);
        transferTo.setId(2);
        transferTo.setFrom(contraAccount);
        transferTo.setTo(account);
        transferTo.setAmount(TEN);
        account.setId(1);
        account.setTransfersFrom(singletonList(transferFrom));
        account.setTransfersTo(singletonList(transferTo));
        contraAccount.setId(2);
        contraAccount.setTransfersTo(singletonList(transferFrom));
        contraAccount.setTransfersFrom(singletonList(transferTo));

        String serialized = MAPPER.writeValueAsString(account);

        assertThat(serialized).isEqualTo(
                MAPPER.writeValueAsString(MAPPER.readTree(fixture("fixtures/account.json"))));
    }

}
