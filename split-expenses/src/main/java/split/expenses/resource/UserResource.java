package split.expenses.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import split.expenses.dto.user.UserCreateDTO;
import split.expenses.service.UserService;

@Path("api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Operation(summary = "Listar Usuários Cadastrados no Sistema", description = "Retorna todos os usuarios cadastrados no sistema")
    @APIResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public Response getUsers() {
        try {
            return Response.ok(userService.findUsers()).build();
        }catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Operation(summary = "Cadastrar Usuário no Sistema", description = "Cadastra um usuário no sistema")
    @APIResponse(responseCode = "201", description = "Usuário cadastrado com sucesso")
    public Response createUser(UserCreateDTO user) {
        try {
            userService.createUser(user);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar Usuário Por ID", description = "Busca um usuário no sistema por ID")
    @APIResponse(responseCode = "200", description = "Usuário retornado com sucesso")
    public Response getUserById(@PathParam("id") long id) {
        try{
            return Response.ok(userService.findUserById(id)).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
