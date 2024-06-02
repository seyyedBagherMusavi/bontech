package co.bontech.exam.service;

import co.bontech.exam.domain.Authority;
import co.bontech.exam.domain.User;
import co.bontech.exam.repository.UserRepository;
import co.bontech.exam.service.dto.UserDTO;
import co.bontech.exam.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    @Transactional
    public void addUserInv(Long userId, Integer debit,int version) {
        userRepository.addDebit(userId,debit,version);
    }

    @Transactional
    public void save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(randomPasswordshareViaEmail());
        user.setAuthority(Authority.USER);
        user.setInventory(0);
        userRepository.save(user);
    }

    private String randomPasswordshareViaEmail() {
        return passwordEncoder.encode("1234");
    }

}
