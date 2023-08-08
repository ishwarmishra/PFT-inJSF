package personalfinancetrackerinweb.controller;

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
import personalfinancetrackerinweb.model.Income;
import personalfinancetrackerinweb.repository.IncomeRepositoryImpl;


@Path("/entry")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IncomeResource implements Serializable {

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    @GET
    public Response getAllIncomes() {
        List<Income> incomes = incomeRepositoryImpl.findAll();
        ResponseModel responseModel = new ResponseModel(200, "Success", incomes);
        return Response.status(200).entity(responseModel).build();
    }

    @GET
    @Path("/{id}")
    public Response getIncomeById(@PathParam("id") long id) {
        Income income = incomeRepositoryImpl.getById(id);
        if (income != null) {
            ResponseModel responseModel = new ResponseModel(200, "Success", income);
            return Response.status(200).entity(responseModel).build();
        } else {
            ResponseModel responseModel = new ResponseModel(404, "Income not found", null);
            return Response.status(404).entity(responseModel).build();
        }
    }

    @POST
    public Response createIncome(Income income) {
        incomeRepositoryImpl.create(income);
        ResponseModel responseModel = new ResponseModel(201, "Data Saved successfully!", income);
        return Response.status(201).entity(responseModel).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateIncome(@PathParam("id") long id, Income income) {
        income.setId(id); // Assuming your Income object has an "id" field
        incomeRepositoryImpl.update(income);
        ResponseModel responseModel = new ResponseModel(200, "Data Updated Successfully!", income);
        return Response.status(200).entity(responseModel).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteIncome(@PathParam("id") long id) {
        incomeRepositoryImpl.delete(id);
        ResponseModel responseModel = new ResponseModel(202, "Data Deleted Successfully!", null);
        return Response.status(202).entity(responseModel).build();
    }
}
