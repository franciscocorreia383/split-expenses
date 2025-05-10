package split.expenses.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import split.expenses.dto.expense.ExpenseCreateDTO;
import split.expenses.dto.expense.ExpenseDTO;
import split.expenses.dto.expense.ExpenseUpgradeDTO;
import split.expenses.entity.ExpenseEntity;
import split.expenses.entity.GroupEntity;
import split.expenses.mapper.ExpenseMapper;
import split.expenses.repository.ExpenseRepository;
import split.expenses.repository.GroupRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ExpenseService {

    @Inject
    private GroupRepository groupRepository;

    @Inject
    private ExpenseRepository expenseRepository;

    @Inject
    private ExpenseMapper expenseMapper;

    @Transactional
    public void createExpense(ExpenseCreateDTO expenseDTO) {
        try {
            GroupEntity group = groupRepository.findById(expenseDTO.getGroupId());

            if(group == null){
                throw new NotFoundException("Group not found");
            }

            ExpenseEntity expenseEntity = expenseMapper.expenseCreateDTOToExpenseEntity(expenseDTO);
            expenseEntity.setGroup(group);
            expenseEntity.setDate(new Date());

            expenseRepository.persist(expenseEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deleteExpense(long expenseId) {
        try {
            ExpenseEntity expenseEntity = expenseRepository.findById(expenseId);

            if(expenseEntity == null){
                throw new NotFoundException("Expense not found");
            }

            expenseRepository.delete(expenseEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void updateExpense(ExpenseUpgradeDTO expenseDTO) {
        try{
            ExpenseEntity expenseEntity = expenseRepository.findById(expenseDTO.getId());

            if(expenseEntity == null){
                throw new NotFoundException("Expense not found");
            }

            expenseEntity.setDescription(expenseDTO.getDescription());
            expenseEntity.setTotal(expenseDTO.getTotal());

            expenseRepository.persist(expenseEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<ExpenseDTO> getAllExpenses(long groupId) {
        try{
            GroupEntity group = groupRepository.findById(groupId);

            if(group == null){
                throw new NotFoundException("Group not found");
            }

            List<ExpenseDTO> expenses = new ArrayList<>();

            expenseRepository.listByGroupId(group.getId()).stream().forEach(expense -> {
                expenses.add(expenseMapper.expenseEntityToExpenseDTO(expense));
            });

            return expenses;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public double getTotalExpensiveGroup(long groupId) {
        GroupEntity group = groupRepository.findById(groupId);

        if(group == null){
            throw new NotFoundException("Group not found");
        }

        double total = expenseRepository
                .listByGroupId(group.getId())
                .stream()
                .mapToDouble(ExpenseEntity::getTotal)
                .sum();


        return total;
    }

}
