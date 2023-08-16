package personalfinancetrackerinweb.api;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.services.LoginService;

@RequestScoped

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class LoginApi {
   
    @Inject
    LoginService loginService; 
    
    @Context
    private HttpServletRequest httpServletRequest; 
    
    @POST
    public Response login(LoginRequest loginRequest) {   
        JwtUtils jwtUtils = new JwtUtils(); 
        
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword(); 
        
        User returnedUser = loginService.userLogin(username, password);    
        if (returnedUser != null) {          
            httpServletRequest.getSession().setAttribute("loggedInUser", returnedUser);
            String jwtToken;
            jwtToken = jwtUtils.generateJwtToken(returnedUser.getUsername());
            RestResponse responseModel = new RestResponse("true",200, "Login successfully!", jwtToken);
            return Response.status(201).entity(responseModel).build();
            //return RestResponse.restResponse("true", "200", "Login Successful",jwtToken);
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Login failed. Invalid credentials.")
                    .build();
        }
    }
}