package split.expenses.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;
import split.expenses.dto.user.UserCreateDTO;
import split.expenses.dto.user.UserDTO;
import split.expenses.entity.UserEntity;
import split.expenses.mapper.UserMapper;
import split.expenses.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    public List<UserDTO> findUsers() {
        List<UserDTO> users = new ArrayList<>();

        userRepository.findAll().stream().forEach(user -> {
            users.add(userMapper.userEntityToUserDTO(user));
        });

        return users;
    }

    @Transactional
    public void createUser(UserCreateDTO userCreateDTO) {
        try {
            UserEntity user = userMapper.userCreateDTOToUserEntity(userCreateDTO);

            user.setPassword(BcryptUtil.bcryptHash(userCreateDTO.getPassword()));
            user.setCreatedAt(new Date());

            userRepository.persist(user);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void inativeUser(Long id) {
        UserEntity user = userRepository.findById(id);
        if(user == null) {
            throw new NotFoundException();
        }

        user.setEnabled(!user.isEnabled());

        userRepository.persist(user);
    }

    public UserDTO findUserById(Long id) {
        UserEntity user = userRepository.findById(id);

        if(user == null) {
            throw new NotFoundException("Usuário não encontrado!");
        }

        return userMapper.userEntityToUserDTO(user);
    }

}
