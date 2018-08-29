package cz.pedry.moneytransfer.resource;

import cz.pedry.moneytransfer.model.Account;
import cz.pedry.moneytransfer.service.AccountService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/account")
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @POST
    @UnitOfWork
    public int create(){
        return accountService.create();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @Produces({MediaType.APPLICATION_JSON})
    public Account get(@PathParam("id") int id) {
        return accountService.findById(id);
    }

}
