package split.expenses.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.annotation.processing.Generated;
import split.expenses.dto.group.GroupCreateDTO;
import split.expenses.dto.group.GroupDTO;
import split.expenses.entity.GroupEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:07:56-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Microsoft)"
)
@ApplicationScoped
public class GroupMapperImpl implements GroupMapper {

    @Inject
    private ParticipantMapper participantMapper;
    @Inject
    private UserMapper userMapper;

    @Override
    public GroupDTO groupEntityToGroupDTO(GroupEntity groupEntity) {
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

    @Override
    public GroupEntity groupDTOToGroupEntity(GroupDTO groupDTO) {
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

    @Override
    public GroupEntity groupCreateDTOToGroup(GroupCreateDTO groupCreateDTO) {
        if ( groupCreateDTO == null ) {
            return null;
        }

        GroupEntity groupEntity = new GroupEntity();

        groupEntity.setName( groupCreateDTO.getName() );
        groupEntity.setCreatedBy( userMapper.userToUserEntity( groupCreateDTO.getCreatedBy() ) );
        groupEntity.setCreatedAt( groupCreateDTO.getCreatedAt() );

        return groupEntity;
    }
}
