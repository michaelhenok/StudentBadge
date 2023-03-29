package miu.cs544.badgemembershipsystem;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.domain.User;
import miu.cs544.badgemembershipsystem.domain.Role;
import miu.cs544.badgemembershipsystem.repository.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class BadgeMembershipSystemApplication {
    private final UserRepo userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(BadgeMembershipSystemApplication.class, args);
    }
    @PostConstruct
    public void initUsers() {
        User defaultUser = new User(1L, "usapkota", bCryptPasswordEncoder.encode("password1") ,null,Role.ADMIN);
        Optional<User> existingUser = userRepository.findByUsername("usapkota");
        if(existingUser.isEmpty()){
            userRepository.save(defaultUser);
        }
    }


}
