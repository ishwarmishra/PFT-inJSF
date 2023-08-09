package personalfinancetrackerinweb.api;

import java.io.Serializable;
import java.util.List;
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
import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.repository.BudgetRepositoryImpl;


@Path("/budgetSource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BudgetRestApi implements Serializable {

    @Inject
    private BudgetRepositoryImpl budgetRepositoryImpl;

    @GET
    public Response getAllBudgets() {
        List<Budget> budgets = budgetRepositoryImpl.findAll();
        RestResponse responseModel = new RestResponse("true",200, "Success", budgets);
        return Response.status(200).entity(responseModel).build();
    }

    @GET
    @Path("/{id}")
    public Response getBudgetById(@PathParam("id") long id) {
        Budget budget = budgetRepositoryImpl.getById(id);
        if (budget != null) {
            RestResponse responseModel = new RestResponse("true",200, "Success", budget);
            return Response.status(200).entity(responseModel).build();
        } else {
            RestResponse responseModel = new RestResponse("false",404, "Budget not found", null);
            return Response.status(404).entity(responseModel).build();
        }
    }

    @POST
    public Response createBudget(Budget budget) {
        budgetRepositoryImpl.create(budget);
        RestResponse responseModel = new RestResponse("true",201, "Data Saved successfully!", budget);
        return Response.status(201).entity(responseModel).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBudget(@PathParam("id") long id, Budget budget) {
        budgetRepositoryImpl.update(budget);
        RestResponse responseModel = new RestResponse("true",200, "Data Updated Successfully!", budget);
        return Response.status(200).entity(responseModel).build();
    }

    @DELETE
    @Path("/{id}")
    
    public Response deleteBudget(@PathParam("id") long id) {
        budgetRepositoryImpl.delete(id);
        RestResponse responseModel = new RestResponse("true",202, "Data Deleted Successfully!", null);
        return Response.status(202).entity(responseModel).build();
    }
}
