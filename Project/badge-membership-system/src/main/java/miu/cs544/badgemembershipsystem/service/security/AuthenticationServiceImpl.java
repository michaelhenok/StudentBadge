package miu.cs544.badgemembershipsystem.service.security;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.InvalidInputException;
import miu.cs544.badgemembershipsystem.dto.request.LoginRequest;
import miu.cs544.badgemembershipsystem.dto.response.LoginResponse;
import miu.cs544.badgemembershipsystem.dto.response.UserResponse;
import miu.cs544.badgemembershipsystem.utils.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if(authentication.isAuthenticated()){
            final UserDetails userdetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            return new LoginResponse(jwtTokenUtil.generateToken(userdetails), modelMapper.map(userdetails, UserResponse.class));
        }else{
            throw new InvalidInputException("Please Enter a Valid Username or Password !!!");
        }
    }
}
