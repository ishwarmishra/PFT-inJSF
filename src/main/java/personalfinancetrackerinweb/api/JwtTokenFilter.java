package personalfinancetrackerinweb.api;

import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(2)
public class JwtTokenFilter implements ContainerRequestFilter {   
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String requestPath = requestContext.getUriInfo().getPath();
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (requestPath.equals("/login")) {
            return;
        } else {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring("Bearer".length()).trim();
                try {
                    String userId = JwtUtils.verifyToken(token);
                    if (userId != null) {
                        return;
                    } else {
                        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    }
                } catch (Exception e) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    return;
                }
            }
        }
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
}

