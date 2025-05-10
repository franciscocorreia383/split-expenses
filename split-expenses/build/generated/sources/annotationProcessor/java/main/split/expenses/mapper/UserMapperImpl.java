package split.expenses.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import javax.annotation.processing.Generated;
import split.expenses.dto.user.UserCreateDTO;
import split.expenses.dto.user.UserDTO;
import split.expenses.dto.user.UserLoginDTO;
import split.expenses.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:07:56-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Microsoft)"
)
@ApplicationScoped
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity userToUserEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userDTO.getId() );
        userEntity.setName( userDTO.getName() );
        userEntity.setEmail( userDTO.getEmail() );

        return userEntity;
    }

    @Override
    public UserEntity userCreateDTOToUserEntity(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setName( userCreateDTO.getName() );
        userEntity.setEmail( userCreateDTO.getEmail() );
        userEntity.setPassword( userCreateDTO.getPassword() );

        return userEntity;
    }

    @Override
    public UserEntity userLoginDTOToUserEntity(UserLoginDTO userLoginDTO) {
        if ( userLoginDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail( userLoginDTO.getEmail() );
        userEntity.setPassword( userLoginDTO.getPassword() );

        return userEntity;
    }

    @Override
    public UserDTO userEntityToUserDTO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        if ( userEntity.getId() != null ) {
            userDTO.id( userEntity.getId() );
        }
        userDTO.name( userEntity.getName() );
        userDTO.email( userEntity.getEmail() );

        return userDTO.build();
    }
}
