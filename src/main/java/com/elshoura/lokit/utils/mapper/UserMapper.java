package com.elshoura.lokit.utils.mapper;

import com.elshoura.lokit.models.dto.request.RegisterRequest;
import com.elshoura.lokit.models.dto.response.AccountResponse;
import com.elshoura.lokit.models.entitys.User;
import com.elshoura.lokit.utils.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    private  UserMapper(){
        throw new IllegalStateException("Utility class can't be instantiated");
    }

    public static User toUser(RegisterRequest registerRequest,PasswordEncoder passwordEncoder){

      return   User.builder()
              .firstName(registerRequest.firstName())
              .lastName(registerRequest.lastName())
              .email(registerRequest.email().toLowerCase())
              .password(passwordEncoder.encode(registerRequest.password()))
              .phone(registerRequest.phone())
              .role(Role.CUSTOMER)
              .enabled(true)
              .emailVerified(true)
              .build();
    }
    public static AccountResponse toAccountResponse(User user){

    return AccountResponse.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .phone(user.getPhone())
            .build();
    }

}
