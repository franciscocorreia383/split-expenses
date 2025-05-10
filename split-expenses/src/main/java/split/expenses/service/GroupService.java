package split.expenses.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import split.expenses.dto.group.GroupCreateDTO;
import split.expenses.dto.group.GroupDTO;
import split.expenses.entity.GroupEntity;
import split.expenses.entity.UserEntity;
import split.expenses.mapper.GroupMapper;
import split.expenses.repository.GroupRepository;
import split.expenses.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GroupService {

    @Inject
    UserRepository userRepository;

    @Inject
    private GroupRepository groupRepository;

    @Inject
    private GroupMapper groupMapper;

    @Transactional
    public void createGroup(GroupCreateDTO group) {
        try{
            GroupEntity groupEntity = groupMapper.groupCreateDTOToGroup(group);
            groupRepository.persist(groupEntity);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<GroupDTO> listGroups() {
        try{
            List<GroupDTO> groups = new ArrayList<>();
            groupRepository.findAll().stream().forEach(group -> {
                groups.add(groupMapper.groupEntityToGroupDTO(group));
            });
            return groups;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deleteGroupById(long id) {
        try{
            GroupEntity group = groupRepository.findById(id);

            if (group == null){
                throw new NotFoundException("Grupo não encontrado!");
            }

            groupRepository.delete(group);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public GroupDTO findGroupById(long id) {
        try{
            GroupEntity group = groupRepository.findById(id);

            if (group == null){
                throw new NotFoundException("Grupo não encontrado!");
            }

            return groupMapper.groupEntityToGroupDTO(group);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<GroupDTO> findGroupsByCreator(long creator) {
        try{
            UserEntity user = userRepository.findById(creator);

            if (user == null){
                throw new NotFoundException("Usuário não encontrado!");
            }

            List<GroupDTO> groups = new ArrayList<>();

            groupRepository.findByCreator(user.getId()).stream().forEach(group -> {
                groups.add(groupMapper.groupEntityToGroupDTO(group));
            });

            return groups;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
