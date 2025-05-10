package split.expenses.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import split.expenses.entity.GroupEntity;

import java.util.List;

@ApplicationScoped
public class GroupRepository implements PanacheRepository<GroupEntity> {
    public List<GroupEntity> findByCreator(Long creator) {
        return find("createdBy.id", creator).stream().toList();
    }

    public List<GroupEntity> findByIdList(List<Long> idList) {
        return list("id IN ?1", idList);
    }
}
