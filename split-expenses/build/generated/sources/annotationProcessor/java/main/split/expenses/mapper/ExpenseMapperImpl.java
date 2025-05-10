package split.expenses.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import split.expenses.dto.expense.ExpenseCreateDTO;
import split.expenses.dto.expense.ExpenseDTO;
import split.expenses.dto.group.GroupDTO;
import split.expenses.entity.ExpenseEntity;
import split.expenses.entity.GroupEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:07:57-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Microsoft)"
)
@ApplicationScoped
public class ExpenseMapperImpl implements ExpenseMapper {

    @Inject
    private ParticipantMapper participantMapper;
    @Inject
    private UserMapper userMapper;

    @Override
    public ExpenseDTO expenseEntityToExpenseDTO(ExpenseEntity expenseEntity) {
        if ( expenseEntity == null ) {
            return null;
        }

        ExpenseDTO.ExpenseDTOBuilder expenseDTO = ExpenseDTO.builder();

        expenseDTO.id( expenseEntity.getId() );
        expenseDTO.description( expenseEntity.getDescription() );
        expenseDTO.total( expenseEntity.getTotal() );
        expenseDTO.totalByUser( expenseEntity.getTotalByUser() );
        expenseDTO.group( groupEntityToGroupDTO( expenseEntity.getGroup() ) );

        return expenseDTO.build();
    }

    @Override
    public ExpenseEntity expenseDTOToExpenseEntity(ExpenseDTO expenseDTO) {
        if ( expenseDTO == null ) {
            return null;
        }

        ExpenseEntity expenseEntity = new ExpenseEntity();

        expenseEntity.setGroup( groupDTOToGroupEntity( expenseDTO.getGroup() ) );
        expenseEntity.setId( (int) expenseDTO.getId() );
        expenseEntity.setDescription( expenseDTO.getDescription() );
        expenseEntity.setTotal( expenseDTO.getTotal() );
        expenseEntity.setTotalByUser( expenseDTO.getTotalByUser() );

        return expenseEntity;
    }

    @Override
    public List<ExpenseDTO> expenseEntityListToExpenseDTOList(List<ExpenseEntity> expenseEntities) {
        if ( expenseEntities == null ) {
            return null;
        }

        List<ExpenseDTO> list = new ArrayList<ExpenseDTO>( expenseEntities.size() );
        for ( ExpenseEntity expenseEntity : expenseEntities ) {
            list.add( expenseEntityToExpenseDTO( expenseEntity ) );
        }

        return list;
    }

    @Override
    public List<ExpenseEntity> expenseDTOListToExpenseEntityList(List<ExpenseDTO> expenseDTOs) {
        if ( expenseDTOs == null ) {
            return null;
        }

        List<ExpenseEntity> list = new ArrayList<ExpenseEntity>( expenseDTOs.size() );
        for ( ExpenseDTO expenseDTO : expenseDTOs ) {
            list.add( expenseDTOToExpenseEntity( expenseDTO ) );
        }

        return list;
    }

    @Override
    public ExpenseEntity expenseCreateDTOToExpenseEntity(ExpenseCreateDTO expenseCreateDTO) {
        if ( expenseCreateDTO == null ) {
            return null;
        }

        ExpenseEntity expenseEntity = new ExpenseEntity();

        expenseEntity.setDescription( expenseCreateDTO.getDescription() );
        expenseEntity.setTotal( expenseCreateDTO.getTotal() );

        return expenseEntity;
    }

    protected GroupDTO groupEntityToGroupDTO(GroupEntity groupEntity) {
        if ( groupEntity == null ) {
            return null;
        }

        GroupDTO.GroupDTOBuilder groupDTO = GroupDTO.builder();

        groupDTO.id( groupEntity.getId() );
        groupDTO.name( groupEntity.getName() );
        groupDTO.createdBy( userMapper.userEntityToUserDTO( groupEntity.getCreatedBy() ) );
        groupDTO.createdAt( groupEntity.getCreatedAt() );
        groupDTO.participants( participantMapper.participantEntityListToParticipantDTOList( groupEntity.getParticipants() ) );

        return groupDTO.build();
    }

    protected GroupEntity groupDTOToGroupEntity(GroupDTO groupDTO) {
        if ( groupDTO == null ) {
            return null;
        }

        GroupEntity groupEntity = new GroupEntity();

        groupEntity.setId( groupDTO.getId() );
        groupEntity.setName( groupDTO.getName() );
        groupEntity.setCreatedBy( userMapper.userToUserEntity( groupDTO.getCreatedBy() ) );
        groupEntity.setCreatedAt( groupDTO.getCreatedAt() );

        return groupEntity;
    }
}
