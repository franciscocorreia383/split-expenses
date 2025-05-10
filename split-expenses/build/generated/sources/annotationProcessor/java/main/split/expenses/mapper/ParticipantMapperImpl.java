package split.expenses.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import split.expenses.dto.group.GroupDTO;
import split.expenses.dto.participant.ParticipantDTO;
import split.expenses.entity.GroupEntity;
import split.expenses.entity.ParticipantEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:07:56-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Microsoft)"
)
@ApplicationScoped
public class ParticipantMapperImpl implements ParticipantMapper {

    @Inject
    private UserMapper userMapper;

    @Override
    public ParticipantDTO participantEntityToDTO(ParticipantEntity participantEntity) {
        if ( participantEntity == null ) {
            return null;
        }

        ParticipantDTO.ParticipantDTOBuilder participantDTO = ParticipantDTO.builder();

        participantDTO.id( participantEntity.getId() );
        participantDTO.user( userMapper.userEntityToUserDTO( participantEntity.getUser() ) );
        participantDTO.group( groupEntityToGroupDTO( participantEntity.getGroup() ) );

        return participantDTO.build();
    }

    @Override
    public ParticipantEntity participantDTOToParticipantEntity(ParticipantDTO participantDTO) {
        if ( participantDTO == null ) {
            return null;
        }

        ParticipantEntity participantEntity = new ParticipantEntity();

        participantEntity.setId( participantDTO.getId() );
        participantEntity.setUser( userMapper.userToUserEntity( participantDTO.getUser() ) );
        participantEntity.setGroup( groupDTOToGroupEntity( participantDTO.getGroup() ) );

        return participantEntity;
    }

    @Override
    public List<ParticipantDTO> participantEntityListToParticipantDTOList(List<ParticipantEntity> participantEntities) {
        if ( participantEntities == null ) {
            return null;
        }

        List<ParticipantDTO> list = new ArrayList<ParticipantDTO>( participantEntities.size() );
        for ( ParticipantEntity participantEntity : participantEntities ) {
            list.add( participantEntityToDTO( participantEntity ) );
        }

        return list;
    }

    @Override
    public List<ParticipantEntity> participantDTOListToParticipantEntityList(List<ParticipantDTO> participantDTOs) {
        if ( participantDTOs == null ) {
            return null;
        }

        List<ParticipantEntity> list = new ArrayList<ParticipantEntity>( participantDTOs.size() );
        for ( ParticipantDTO participantDTO : participantDTOs ) {
            list.add( participantDTOToParticipantEntity( participantDTO ) );
        }

        return list;
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
