package split.expenses.service;

import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import split.expenses.dto.group.GroupDTO;
import split.expenses.dto.participant.ParticipantCreateDTO;
import split.expenses.dto.participant.ParticipantDeleteDTO;
import split.expenses.entity.GroupEntity;
import split.expenses.entity.ParticipantEntity;
import split.expenses.entity.UserEntity;
import split.expenses.mapper.GroupMapper;
import split.expenses.mapper.ParticipantMapper;
import split.expenses.repository.GroupRepository;
import split.expenses.repository.ParticipantRepository;
import split.expenses.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ParticipantService {

    @Inject
    GroupRepository groupRepository;
    @Inject
    private ParticipantRepository participantRepository;

    @Inject
    private ParticipantMapper participantMapper;

    @Inject
    private GroupMapper groupMapper;

    @Inject
    private UserRepository userRepository;

    @Transactional
    public void createParticipant(ParticipantCreateDTO participantDTO) {
        UserEntity user = userRepository.findById(participantDTO.getUser().getId());
        GroupEntity group = groupRepository.findById(participantDTO.getGroup().getId());

        if (group == null || user == null) {
            throw new NotFoundException("Usuário ou Grupo não encontrado!");
        }

        if (participantRepository.participantValidation(group.getId(), user.getId())) {
            throw new UnauthorizedException("Usuário já está cadastrado no grupo!");
        }

        ParticipantEntity participant = new ParticipantEntity();
        participant.setUser(user);
        participant.setGroup(group);
        participant.setDate(LocalDateTime.now());

        participantRepository.persist(participant);

    }

    public List<GroupDTO> getGroupsByUserId(long userId) {
        try {
            List<Long> idList = new ArrayList<>();
            List<GroupDTO> groups = new ArrayList<>();

            UserEntity user = userRepository.findById(userId);

            if (user == null) {
                throw new NotFoundException("Usuário não encontrado!");
            }

            participantRepository.findByUserId(user.getId()).stream().forEach(participant -> {
                idList.add(participant.getGroup().getId());
            });

            groupRepository.findByIdList(idList).stream().forEach(group -> {
                groups.add(groupMapper.groupEntityToGroupDTO(group));
            });

            return groups;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deleteParticipant(ParticipantDeleteDTO participantDTO) {
        try {
            UserEntity user = userRepository.findById(participantDTO.getUserId());
            GroupEntity group = groupRepository.findById(participantDTO.getGroupId());

            if (group == null || user == null) {
                throw new NotFoundException("Usuário ou grupo não encontrado!");
            }

            ParticipantEntity participant = participantRepository.findParticipant(user.getId(), group.getId());

            if (participant == null) {
                throw new NotFoundException("Usuário não contrado no grupo!");
            }

            participantRepository.delete(participant);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
