package cz.pedry.moneytransfer.resource;

import cz.pedry.moneytransfer.service.TransferService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;

import static org.mockito.Mockito.mock;

public class TransferResourceTest {

    // ANALOGICAL TO AccountResourceTest

    private static final TransferService transferService = mock(TransferService.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new TransferResource(transferService))
            .build();

    public void testSave() {

    }

    public void testGet() {

    }
}
