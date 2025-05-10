package split.expenses.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import split.expenses.dto.group.GroupCreateDTO;
import split.expenses.dto.group.GroupDTO;
import split.expenses.entity.GroupEntity;

@Mapper(componentModel = "cdi", uses = {ParticipantMapper.class, UserMapper.class})
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDTO groupEntityToGroupDTO(GroupEntity groupEntity);

    @Mapping(target = "participants", ignore = true)
    GroupEntity groupDTOToGroupEntity(GroupDTO groupDTO);

    GroupEntity groupCreateDTOToGroup(GroupCreateDTO groupCreateDTO);

}
