package split.expenses.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import split.expenses.entity.ParticipantEntity;

import java.util.List;

@ApplicationScoped
public class ParticipantRepository implements PanacheRepository<ParticipantEntity> {
    public boolean participantValidation(Long groupId, Long userId) {
        return count("user.id = ?1 and group.id = ?2", userId, groupId) > 0;
    }

    public List<ParticipantEntity> findByUserId(Long userId) {
        return find("user.id = ?1", userId).list();
    }

    public ParticipantEntity findParticipant(Long userId, Long groupId) {
        return find("user.id = ?1 and group.id = ?2", userId, groupId).firstResult();
    }

}
