package co.bontech.exam.service;

import co.bontech.exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Transactional
    public void addUserInv(Long userId, Integer debit,int version) {
        userRepository.addDebit(userId,debit,version);
    }


}
