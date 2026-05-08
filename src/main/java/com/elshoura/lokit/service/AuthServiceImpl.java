package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.InvalidCredentialsException;
import com.elshoura.lokit.errors.exception.UserAlreadyExistException;
import com.elshoura.lokit.errors.exception.UserNotFoundException;
import com.elshoura.lokit.models.dto.request.LoginRequest;
import com.elshoura.lokit.models.dto.request.RegisterRequest;
import com.elshoura.lokit.models.dto.response.AuthResponse;
import com.elshoura.lokit.models.entitys.User;
import com.elshoura.lokit.repository.UserRepository;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.security.JwtService;
import com.elshoura.lokit.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final JwtService jwtService;
private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        String email=request.email().toLowerCase();

    if(userRepository.existsByEmail(email)){
    throw new UserAlreadyExistException("User already exists with email :"+email);
    }
    User user= UserMapper.toUser(request,passwordEncoder);

            User savedUser=userRepository.save(user);
    String token=jwtService.generateToken(new CustomUserDetails(savedUser));

    return  new AuthResponse(token,"Successfully  Signup ON Lokit");
    }
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        try{

       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       loginRequest.email(),
                       loginRequest.password()
               )
       );
        }catch (BadCredentialsException ex){
            throw new InvalidCredentialsException("Invalid email or password");
        }
       User user =userRepository.findByEmail(loginRequest.email().toLowerCase())
               .orElseThrow(()-> new UserNotFoundException("User not found with email :"+loginRequest.email()));

      String token=jwtService.generateToken(new CustomUserDetails(user));

       return new AuthResponse(token," You have Successfully registered in our Lokit and start working in it");

    }


}
