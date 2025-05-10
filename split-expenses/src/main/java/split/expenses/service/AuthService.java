package split.expenses.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.UnauthorizedException;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import split.expenses.configuration.TokenConfiguration;
import split.expenses.dto.auth.AuthDTO;
import split.expenses.dto.user.UserLoginDTO;
import split.expenses.entity.UserEntity;
import split.expenses.mapper.UserMapper;
import split.expenses.repository.UserRepository;

import java.lang.module.Configuration;
import java.time.Duration;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    @Inject
    TokenConfiguration tokenConfiguration;

    public AuthDTO login(UserLoginDTO userLoginDTO) {
        UserEntity user = userValidadation(userLoginDTO.getEmail());

        if (BcryptUtil.matches(userLoginDTO.getPassword(), user.getPassword())) {

            AuthDTO authDTO = new AuthDTO();
            authDTO.setToken(tokenConfiguration.generateJWT());
            return authDTO;
        } else {
            throw new UnauthorizedException("Usuário ou senha incorretos");
        }
    }

    private UserEntity userValidadation(String email) {
        UserEntity user = userRepository.findByEmail(email);

        if (user == null) {
            throw new NotFoundException("Usuário não encontrado!");
        } else{
            return user;
        }
    }

}
