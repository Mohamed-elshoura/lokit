package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.AlreadyExistException;
import com.elshoura.lokit.errors.exception.InvalidPasswordException;
import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.models.dto.request.ChangePasswordRequest;
import com.elshoura.lokit.models.dto.request.UpdateAccountRequest;
import com.elshoura.lokit.models.dto.response.AccountResponse;
import com.elshoura.lokit.models.entitys.User;
import com.elshoura.lokit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.elshoura.lokit.utils.mapper.UserMapper.toAccountResponse;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AccountResponse getMyAccount(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return toAccountResponse(user);
    }

    @Override
    public AccountResponse updateMyAccount(Long userId, UpdateAccountRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!user.getEmail().equals(request.email())
                && userRepository.existsByEmail(request.email())) {
            throw new AlreadyExistException("Email already exists");
        }

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPhone(request.phone());

        return toAccountResponse(userRepository.save(user));
    }
    @Override
    public void changePassword(Long userId, ChangePasswordRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Old password is incorrect");
        }

        if (!request.newPassword().equals(request.confirmNewPassword())) {
            throw new InvalidPasswordException("New password and confirm password do not match");
        }

        if (passwordEncoder.matches(request.newPassword(), user.getPassword())) {
            throw new InvalidPasswordException("New password must be different from old password");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));

        userRepository.save(user);
    }

}
