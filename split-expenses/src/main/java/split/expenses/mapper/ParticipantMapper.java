package split.expenses.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import split.expenses.dto.participant.ParticipantDTO;
import split.expenses.entity.ParticipantEntity;

import java.util.List;

@Mapper(componentModel = "cdi", uses = {UserMapper.class})
public interface ParticipantMapper {

    ParticipantMapper INSTANCE = Mappers.getMapper(ParticipantMapper.class);

    @Mapping(target = "group.participants", ignore = true)
    ParticipantDTO participantEntityToDTO(ParticipantEntity participantEntity);

    @Mapping(target = "group.participants", ignore = true)
    ParticipantEntity participantDTOToParticipantEntity(ParticipantDTO participantDTO);

    List<ParticipantDTO> participantEntityListToParticipantDTOList(List<ParticipantEntity> participantEntities);

    List<ParticipantEntity> participantDTOListToParticipantEntityList(List<ParticipantDTO> participantDTOs);


}
