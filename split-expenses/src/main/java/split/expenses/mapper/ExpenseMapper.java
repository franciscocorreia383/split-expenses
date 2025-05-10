package split.expenses.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import split.expenses.dto.expense.ExpenseCreateDTO;
import split.expenses.dto.expense.ExpenseDTO;
import split.expenses.entity.ExpenseEntity;

import java.util.List;

@Mapper(componentModel = "cdi", uses = {ParticipantMapper.class, UserMapper.class})
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    ExpenseDTO expenseEntityToExpenseDTO(ExpenseEntity expenseEntity);

    @Mapping(target = "group.participants", ignore = true)
    @Mapping(target = "date", ignore = true)
    ExpenseEntity expenseDTOToExpenseEntity(ExpenseDTO expenseDTO);

    List<ExpenseDTO> expenseEntityListToExpenseDTOList(List<ExpenseEntity> expenseEntities);

    List<ExpenseEntity> expenseDTOListToExpenseEntityList(List<ExpenseDTO> expenseDTOs);

    ExpenseEntity expenseCreateDTOToExpenseEntity(ExpenseCreateDTO expenseCreateDTO);

}
