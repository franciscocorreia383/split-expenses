package split.expenses.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import split.expenses.entity.ExpenseEntity;

import java.util.List;

@ApplicationScoped
public class ExpenseRepository implements PanacheRepository<ExpenseEntity> {

    public List<ExpenseEntity> listByGroupId(Long groupId) {
        return find("group.id = ?1", groupId).list();
    }

}
