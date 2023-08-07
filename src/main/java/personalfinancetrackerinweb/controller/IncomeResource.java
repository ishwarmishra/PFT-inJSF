package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import personalfinancetrackerinweb.model.Income;
import personalfinancetrackerinweb.repository.IncomeRepositoryImpl;

/**
 *
 * @author ishwar
 */
@ApplicationScoped
@Path("/entry")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IncomeResource implements Serializable {

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Income> getAllIncomes() {
        return incomeRepositoryImpl.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Income getIncomeById(@PathParam("id") long id) {
        return incomeRepositoryImpl.getById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIncome(Income income) {
        incomeRepositoryImpl.create(income);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIncome(@PathParam("id") long id, Income income) {
        incomeRepositoryImpl.update(income);
        return Response.ok().build();

    }

    @DELETE
    @Path("/{id}")
    public void deleteIncome(@PathParam("id") long id) {
        incomeRepositoryImpl.delete(id);

    }
}
