package split.expenses.resource;

import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import split.expenses.dto.participant.ParticipantCreateDTO;
import split.expenses.dto.participant.ParticipantDeleteDTO;
import split.expenses.service.ParticipantService;

@Path("api/v1/groups")
@Produces(MediaType.APPLICATION_JSON)
public class ParticipantResource {

    @Inject
    private ParticipantService participantService;

    @POST
    @RolesAllowed("USER")
    @Operation(summary = "Cadastrar Participante Em Grupo No Sistema", description = "Cria um novo participante a um grupo existente no sistema")
    @APIResponse(responseCode = "201", description = "Participante criado com sucesso")
    public Response createParticipant(ParticipantCreateDTO participantDTO){
        try{
            participantService.createParticipant(participantDTO);
            return Response.status(Response.Status.CREATED).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (UnauthorizedException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{userId}")
    @RolesAllowed("USER")
    @Operation(summary = "Buscar Grupos Por Usuário", description = "Lista os grupos de um determinado participante")
    @APIResponse(responseCode = "200", description = "Lista de grupos retornada com sucesso")
    public Response getGruposByUserId(@PathParam("userId") Long userId){
        try{
            return Response.ok(participantService.getGroupsByUserId(userId)).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @RolesAllowed("USER")
    @Operation(summary = "Deletar Participante de Grupo", description = "Deleta um participante de um grupo")
    @APIResponse(responseCode = "200", description = "Participante deletado com sucesso")
    public Response deleteParticipant(ParticipantDeleteDTO deleteDTO) {
        try{
            participantService.deleteParticipant(deleteDTO);
            return Response.ok().build();
        }catch(NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
