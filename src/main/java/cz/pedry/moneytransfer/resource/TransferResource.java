package cz.pedry.moneytransfer.resource;

import cz.pedry.moneytransfer.model.Transfer;
import cz.pedry.moneytransfer.service.TransferService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/transfer")
public class TransferResource {

    private final TransferService transferService;

    public TransferResource(TransferService transferService) {
        this.transferService = transferService;
    }

    @POST
    @UnitOfWork
    @Consumes({MediaType.APPLICATION_JSON})
    public long save(@Valid Transfer transfer){
        return transferService.save(transfer);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @Produces({MediaType.APPLICATION_JSON})
    public Transfer get(@PathParam("id") int id){
        return transferService.findById(id);
    }

}
