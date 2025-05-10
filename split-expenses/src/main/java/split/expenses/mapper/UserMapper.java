package split.expenses.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import split.expenses.dto.user.UserDTO;
import split.expenses.dto.user.UserCreateDTO;
import split.expenses.dto.user.UserLoginDTO;
import split.expenses.entity.UserEntity;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "groupsCreated", ignore = true)
    @Mapping(target = "participations", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    UserEntity userToUserEntity(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "groupsCreated", ignore = true)
    @Mapping(target = "participations", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    UserEntity userCreateDTOToUserEntity(UserCreateDTO userCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "groupsCreated", ignore = true)
    @Mapping(target = "participations", ignore = true)
    UserEntity userLoginDTOToUserEntity(UserLoginDTO userLoginDTO);

    UserDTO userEntityToUserDTO(UserEntity userEntity);


}
