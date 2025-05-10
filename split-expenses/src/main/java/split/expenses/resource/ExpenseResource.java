package split.expenses.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import split.expenses.dto.expense.ExpenseCreateDTO;
import split.expenses.dto.expense.ExpenseUpgradeDTO;
import split.expenses.service.ExpenseService;

@Path("api/v1/expense")
@Produces(MediaType.APPLICATION_JSON)
public class ExpenseResource {

    @Inject
    private ExpenseService expenseService;

    @POST
    @Operation(summary = "Cadastrar Uma Despesa A Um Grupo", description = "Cadastra uma nova despesa a um grupo")
    @APIResponse(responseCode = "201", description = "Despesa criada com sucesso")
    public Response createExpense(ExpenseCreateDTO expenseDTO) {
        try {
            expenseService.createExpense(expenseDTO);
            return Response.status(Response.Status.CREATED).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Operation(summary = "Atualizar Uma Despesa", description = "Atualiza uma despesa de um determinado grupo")
    @APIResponse(responseCode = "200", description = "Despesa alterada com sucesso")
    public Response updateExpense(ExpenseUpgradeDTO expenseDTO) {
        try{
            expenseService.updateExpense(expenseDTO);
            return Response.status(Response.Status.OK).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletar Despesa no Sistema", description = "Deleta uma despesa cadastrado no sistema")
    @APIResponse(responseCode = "200", description = "Despesa deletado com sucesso")
    public Response deleteExpense(@PathParam("id") Long id) {
        try{
            expenseService.deleteExpense(id);
            return Response.status(Response.Status.OK).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{group_id}")
    @Operation(summary = "Listar Despesa do Grupo", description = "Lista as despesas cadastradas no grupo")
    @APIResponse(responseCode = "200", description = "Despesa listadas com sucesso")
    public Response getExpense(@PathParam("group_id") Long id) {
        try{
            return Response.ok(expenseService.getAllExpenses(id)).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/total/{group_id}")
    @Operation(summary = "Listar Valor das Despesa do Grupo", description = "Lista a soma das despesas despesas cadastradas no grupo")
    @APIResponse(responseCode = "200", description = "Despesa listadas com sucesso")
    public Response getExpenseValue(@PathParam("group_id") Long id) {
        try{
            return Response.ok(expenseService.getTotalExpensiveGroup(id)).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


}
