package split.expenses.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import split.expenses.dto.group.GroupCreateDTO;
import split.expenses.dto.group.GroupDTO;
import split.expenses.service.GroupService;

@Path("api/v1/group")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {

    @Inject
    private GroupService groupService;

    @POST
    @RolesAllowed("USER")
    @Operation(summary = "Cadastrar Grupo no Sistema", description = "Cria um novo grupo no sistema")
    @APIResponse(responseCode = "201", description = "Grupo criado com sucesso")
    public Response createGroup(GroupCreateDTO groupDTO) {
        try {
            groupService.createGroup(groupDTO);
            return Response.status(Response.Status.CREATED).build();
        }catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @RolesAllowed("USER")
    @Operation(summary = "Listar Grupos Cadastrados no Sistema", description = "Retorna todos os grupos cadastrados no sistema")
    @APIResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public Response getAllGroups() {
            try{
                return Response.ok(groupService.listGroups()).build();
            } catch (RuntimeException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }
    }

    @DELETE
    @RolesAllowed("USER")
    @Path("/{id}")
    @Operation(summary = "Deletar Grupo no Sistema", description = "Deleta um grupo cadastrado no sistema")
    @APIResponse(responseCode = "200", description = "Grupo deletado com sucesso")
    public Response deleteGroup(@PathParam("id") int id) {
        try{
            groupService.deleteGroupById(id);
            return Response.status(Response.Status.OK).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @RolesAllowed("USER")
    @Path("/{id}")
    @Operation(summary = "Buscar Grupo Cadastrado Por ID", description = "Busca um grupo cadastrado no sistema por ID")
    @APIResponse(responseCode = "200", description = "Grupo deletado com sucesso")
    public Response setGroup(@PathParam("id") int id) {
        try {
            GroupDTO group = groupService.findGroupById(id);
            return Response.ok(group).build();
        }catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @GET
    @RolesAllowed("USER")
    @Path("/byUser/{id}")
    @Operation(summary = "Buscar Grupos Por ID de Criador", description = "Lista os grupos pelo ID de quem cadastrou!")
    @APIResponse(responseCode = "200", description = "Lista de Grupos retornada com sucesso")
    public Response getGroupByUser(@PathParam("id") int id) {
        try{
            return Response.ok(groupService.findGroupsByCreator(id)).build();
        }catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
