package split.expenses.resource;

import io.quarkus.security.UnauthorizedException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import split.expenses.dto.user.UserLoginDTO;
import split.expenses.service.AuthService;

@Path("api/v1/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Autenticar Usuário no Sistema", description = "Autentica um usuário cadastrado no sistema")
    @APIResponse(responseCode = "200", description = "Usuário autenticado com sucesso")
    public Response Auth(UserLoginDTO userLoginDTO) {
        try {
            return Response.ok().entity(authService.login(userLoginDTO)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (UnauthorizedException u) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
