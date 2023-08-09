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

import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;

@Path("/categorySource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryRestApi implements Serializable{
    
    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;
    
     @GET
    public Response getAllCategories() {
        List<Category> categories = categoryRepositoryImpl.findAll();
        RestResponse responseModel = new RestResponse("true",200, "Success", categories);
        return Response.status(200).entity(responseModel).build();
    }

    @GET
    @Path("/{id}")
    public Response getCategoryById(@PathParam("id") long id) {
        Category category = categoryRepositoryImpl.getById(id);
        if (category != null) {
            RestResponse responseModel = new RestResponse("true",200, "Success", category);
            return Response.status(200).entity(responseModel).build();
        } else {
            RestResponse responseModel = new RestResponse("false",404, "Category not found", null);
            return Response.status(404).entity(responseModel).build();
        }
    }

    @POST
    public Response createCategory(Category category) {
        categoryRepositoryImpl.create(category);
        RestResponse responseModel = new RestResponse("false",201, "Data Saved successfully!", category);
        return Response.status(201).entity(responseModel).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCategory(@PathParam("id") long id, Category category) {
        categoryRepositoryImpl.update(category);
        RestResponse responseModel = new RestResponse("true",200, "Data Updated Successfully!", category);
        return Response.status(200).entity(responseModel).build();
    }

    @DELETE
    @Path("/{id}")
    
    public Response deleteCategory(@PathParam("id") long id) {
        categoryRepositoryImpl.delete(id);
        RestResponse responseModel = new RestResponse("true",202, "Data Deleted Successfully!", null);
        return Response.status(202).entity(responseModel).build();
    }
    
}
